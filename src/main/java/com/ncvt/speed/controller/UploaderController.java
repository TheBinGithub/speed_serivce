package com.ncvt.speed.controller;

import com.ncvt.speed.entity.FileEntity;
import com.ncvt.speed.params.UploaderParams;
import com.ncvt.speed.service.FileService;
import com.ncvt.speed.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Arrays;

@Api(tags = "上传模块")
@RestController
@Slf4j
public class UploaderController {

    @Resource
    private FileService fileService;

    @Value("${file-save-path}")
    private String path;


    @Value("${file-temp-path}")
    private String temppath;


    private static final String utf8 = "utf-8";

    @ApiOperation(value = "单个上传")
    @PostMapping("/upload/{id}")
    public Result upload(@PathVariable String id,FileEntity fileEntity, MultipartFile Mfile, HttpServletRequest req) {
        if (Mfile == null) return Result.fail(400,"请上传文件");
        //获取文件的名字
        String originName = Mfile.getOriginalFilename();
        System.out.println("originName:" + originName);

        if (originName == null){
            return Result.fail(400,"文件名不能为空！");
        }
        //判断文件类型
//        if (!originName.endsWith(".jpg") && !originName.endsWith(".png")) {
//            return Result.fail("文件格式不正确");
//        }

        File pathFile = new File(path+id);
        if (!pathFile.exists()){
            pathFile.mkdirs();
        }
        File file = new File(path+id,originName);
        log.info(file.getPath());

        try {
//            req.setCharacterEncoding(utf8);

            // 此处Mfile转为File, 好像速度都差不多？
//            long s1 = System.currentTimeMillis();
//            FileUtils.copyInputStreamToFile(Mfile.getInputStream(),file);
//            long e1 = System.currentTimeMillis();
//            log.info("time1:"+(e1-s1)+"/ms");

            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));

            BufferedInputStream bis = new BufferedInputStream(Mfile.getInputStream());
            byte[] b = new byte[1024 * 1024 * 10];
            int len = 0;

            long s2 = System.currentTimeMillis();
            while ((len = bis.read(b)) != -1){
                bos.write(b,0,len);
            }
            long e2 = System.currentTimeMillis();
            log.info("time2:"+(e2-s2)+"/ms");

            bos.close();
            bis.close();

            // 数据库
//            fileEntity.setDuYou(true);

            return fileService.addFile(fileEntity,"");

            //生成返回给前端的url
//            String url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/api/images" + format + newName;

        } catch (Exception e) {
            return Result.fail("服务端异常",e.getMessage());
        } finally {

        }

    }

    public static void deleteFile(File file){
        try {
            File[] files = file.listFiles();
            if (files!=null){//如果包含文件进行删除操作
                for (File f:files) {
                    //判断遍历出的文件是不是文件
                    if (f.isFile()){
                        //如果是则直接删除
                        f.delete();
                    }else {
                        deleteFile(f);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    

    @ApiOperation(value = "分片上传")
    @PostMapping("/fupload/{id}")
    public Result fupload(@PathVariable String id,FileEntity fileEntity, MultipartFile MFile, HttpServletRequest req){

        Integer shunk = Integer.valueOf(req.getParameter("chunk"));
        Integer shunks = Integer.valueOf(req.getParameter("chunks"));

        if (MFile == null) return Result.fail(400,"请上传文件");

        //  获取文件的名字
        String originName = req.getParameter("originName");
        // 判断文件名不能为空
        if (originName == null) return Result.fail(400,"文件名不能为空！");
        // new一个临时目录的File对象
        File temppath1 = new File(temppath+id);
        // 不存在则创建
        if (!temppath1.exists()){
            temppath1.mkdirs();
        }
        // new一个分片文件的File对象
        File shunkFile = new File(temppath1,shunk+"_"+originName);
        System.out.println(shunkFile.getPath()+"--"+shunkFile.exists());
        // 判断该分片是否存在
        if (!shunkFile.exists()){
            try(
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(shunkFile));
                BufferedInputStream bis = new BufferedInputStream(MFile.getInputStream())
            ){
                // 不存在则
                byte[] b = new byte[1024 * 1024 * 10];
                int len = 0;

                long s = System.currentTimeMillis();
                while ((len = bis.read(b)) != -1){
                    bos.write(b,0,len);
//                    bos.flush();
                    bos.close();
                }
                long e = System.currentTimeMillis();

                log.info("time2:"+(e-s)+"/ms");

                // 合并
                if (shunk.intValue() == shunks.intValue()-1){
                    // new一个最终文件存放的位置File对象
                    File f = new File(path+id);
                    if (!f.exists()){
                        f.mkdirs();
                    }
                    // new一个最终的文件File对象
                    File endFile = new File(path+id,originName);
                    // 循环拿出分片
                    for (int i=0; i<shunks; i++){
                        // new一个当前取到的分片File对象
                        File iFile = new File(temppath1,i+"_"+originName);
                        System.out.println("取出分片"+iFile.getPath());
                        if (!iFile.exists()){
                            return Result.fail("分片不存在："+iFile.getPath());
                        }
                        // 如果取到的分片不存在则休眠1000ms后继续判断
//                        while (!iFile.exists()){
//                            Thread.sleep(1000);
//                        }
                        try(
                            BufferedOutputStream endBos = new BufferedOutputStream(new FileOutputStream(endFile,true));
                            BufferedInputStream endBis = new BufferedInputStream(new FileInputStream(iFile));
                        ) {
                            byte[] b1 = new byte[1024 * 1024 * 10];
                            int len1 = 0;
                            while ((len1 = endBis.read(b1)) != -1){
                                endBos.write(b1,0,len1);
                                endBos.flush();
                            }
                        }catch (Exception endE){
                            endE.printStackTrace();
                            return Result.fail("合并出现异常！",endE.getMessage());
                        }
                    }

                    // 需要做删除临时目录
                    deleteFile(temppath1);
                    boolean result = temppath1.delete();
                    if (!result){
                        return Result.fail("删除临时目录出现异常！");
                    }

                    // 数据库
                    return fileService.addFile(fileEntity,"path: "+endFile.getPath());
                }

                return Result.ok(201,"分片成功！");

            }catch (Exception e){
                e.printStackTrace();
                return Result.fail("服务端异常！",e.getMessage());
            }
        }else {
            // 存在则不上传
            return Result.ok(202,"该分片已存在！");
        }

    }
}
