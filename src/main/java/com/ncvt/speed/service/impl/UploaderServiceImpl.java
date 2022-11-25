package com.ncvt.speed.service.impl;

import com.ncvt.speed.entity.FileEntity;
import com.ncvt.speed.mapper.FileMapper;
import com.ncvt.speed.mapper.UserMapper;
import com.ncvt.speed.params.UploaderParams;
import com.ncvt.speed.service.FileService;
import com.ncvt.speed.service.UploaderService;
import com.ncvt.speed.util.Md5;
import com.ncvt.speed.util.RecursiveDeletion;
import com.ncvt.speed.util.Result;
import com.ncvt.speed.util.SavePath;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class UploaderServiceImpl implements UploaderService {

    private final String path = SavePath.savePath();

    private final String temppath = SavePath.tempPath();

    @Resource
    private FileService fileService;

    @Resource
    private FileMapper fileMapper;

    @Resource
    private UserMapper userMapper;

    // 分片上传
    @Override
    public Result upload(String id, FileEntity fileEntity, MultipartFile MFile, HttpServletRequest req,Long s) {

        // 判断是否秒传
        List<FileEntity> hashs = fileMapper.queryHash(id,fileEntity.getHash());
        if (hashs.size() != 0) {
            hashs.get(0).setBelongId(fileEntity.getBelongId());
            hashs.get(0).setDeleteId(0);
            long e = System.currentTimeMillis();
            System.out.println("time:"+(e-s)+"/ms");
            return fileService.addFile(hashs.get(0),"秒传成功！");
        }
//        Integer shunk = Integer.valueOf(req.getParameter("chunk"));
        if (MFile == null) return Result.fail(400,"请上传文件");
        // 取出所需参数
        Integer shunk = fileEntity.getShunk();
        Integer shunks = fileEntity.getShunks();
        String originName = fileEntity.getFileName();

        // 判断文件名不能为空
        if (originName == null) return Result.fail(400,"文件名不能为空！");
        // new一个临时目录的File对象
        File temppath1 = new File(temppath+id, fileEntity.getHash());
        // 不存在则创建
        if (!temppath1.exists()) temppath1.mkdirs();
//        System.out.println("temppath1:"+temppath1.getAbsolutePath()+"  b:"+temppath1.exists());
        // new一个分片文件的File对象
        File shunkFile = new File(temppath1,shunk+"_"+originName);
        // 判断该分片是否存在
        if (!shunkFile.exists()){
            try(
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(shunkFile));
                BufferedInputStream bis = new BufferedInputStream(MFile.getInputStream())
            ){
                // 不存在则开始上传
                byte[] b = new byte[1024 * 1024 * 8];
                int len = 0;
//                long s = System.currentTimeMillis();
                // 使用缓冲流边读边写
                while ((len = bis.read(b)) != -1){
                    bos.write(b,0,len);
                    bos.flush();
                }
                // close
                if (bos != null) bos.close();
//                System.out.println("shunkFile:"+shunkFile.getAbsolutePath()+"  b:"+shunkFile.exists());
                // 判断该分片是否上传完成
                if (len != -1){
                    // 未完成则删除该分片残余数据
                    boolean sf = shunkFile.delete();
                    if (!sf) return Result.fail("删除损坏的分片出现异常！",shunkFile.getPath());
                    return Result.fail("分片上传出现异常！",shunkFile.getPath());
                }
                // 合并
                if (shunk.intValue() == shunks.intValue()-1){
                    // new一个最终文件存放目录的File对象(目录)
                    File f = new File(path+id);
                    // 存放目录不存在则new一个
                    if (!f.exists()) f.mkdirs();
//                    System.out.println("最终目录:"+f.getAbsolutePath()+"  b:"+f.exists());
                    // new一个最终文件的File对象(文件)
//                    File endFile = new File(path+id+fileEntity.getBelong(),originName);
                    String salt = UUID.randomUUID().toString().toUpperCase();
                    String[] type = fileEntity.getFileName().split("\\.");
                    File endFile = new File(f.getPath(), Md5.getMd5Password(originName,salt)+"."+type[type.length - 1]);
                    // 循环拿出分片
                    for (int i=0; i<shunks; i++){
                        // new一个当前取到的分片File对象
                        File iFile = new File(temppath1,i+"_"+originName);
//                        log.info("取出分片"+iFile.getPath());
                        // 分片不存在则return
                        if (!iFile.exists()) return Result.ok(404,"合并时，分片不存在："+iFile.getPath());
                        // 如果取到的分片不存在则休眠1000ms后继续判断
//                        while (!iFile.exists()){
//                            Thread.sleep(1000);
//                        }
                        // 捕获一下, 在try()里可传入流
                        try(
                            BufferedOutputStream endBos = new BufferedOutputStream(new FileOutputStream(endFile,true));
                            BufferedInputStream endBis = new BufferedInputStream(new FileInputStream(iFile))
                        ) {
                            byte[] b1 = new byte[1024 * 1024 * 8];
                            int len1 = 0;
                            // 使用缓冲流边读边写，已开启追加
                            while ((len1 = endBis.read(b1)) != -1){
                                endBos.write(b1,0,len1);
                                endBos.flush();
                            }
                            if (endBos != null) endBos.close();
                        }catch (Exception endE){
                            endE.printStackTrace();
                            return Result.fail("合并出现异常！",endE.getMessage());
                        }
                    }
//                    System.out.println("最终文件:"+endFile.getAbsolutePath()+"  b:"+endFile.exists());
                    // 删除临时目录
                    RecursiveDeletion.deleteFile(temppath1);
                    boolean result = temppath1.delete();
                    if (!result) return Result.fail("删除临时目录出现异常！");
                    // 修改容量
                    int uResult = userMapper.modifyUserBySpace(id,endFile.length());
                    if (uResult != 1) return Result.fail("修改容量出现未知异常！");
                    // 数据库添加记录
                    fileEntity.setFilePath(id+"@-.@"+endFile.getName());
                    fileEntity.setBelongId(fileEntity.getBelongId());
                    FileEntity fileEntity1 = FileEntity.getFE(id,endFile,fileEntity);
                    fileEntity1.setFolderBelongId("0");
                    return fileService.addFile(fileEntity1,"100%");
                }
//                String.format("%.2f", (shunk/shunks)*100)+"%"
                return Result.ok(201,"分片成功！","null");
            }catch (Exception e){
                e.printStackTrace();
                return Result.fail("服务端异常！",e.getMessage());
            }
        }else {
            // 存在则不上传
            return Result.ok(202,"该分片已存在！");
        }
    }

    // 取消上传
    @Override
    public Result endUpload(String id, UploaderParams uploaderParams) {
        try {
            File file = new File(temppath+id,uploaderParams.getOriginName());
            RecursiveDeletion.deleteFile(file);
            boolean result =  file.delete();
            if (file.exists() && result) return Result.fail("删除临时文件出现未知异常！");
            return Result.ok("取消成功！");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("取消上传出现未知异常！",e.getMessage());
        }
    }

}
