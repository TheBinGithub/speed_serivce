package com.ncvt.speed.service.impl;

import com.ncvt.speed.entity.BelongEntity;
import com.ncvt.speed.entity.DeleteEntity;
import com.ncvt.speed.entity.FileEntity;
import com.ncvt.speed.mapper.BelongMapper;
import com.ncvt.speed.mapper.DeleteMapper;
import com.ncvt.speed.mapper.FileMapper;
import com.ncvt.speed.params.MovementParams;
import com.ncvt.speed.params.RecyclerParams;
import com.ncvt.speed.params.RenameParams;
import com.ncvt.speed.service.OperationFileService;
import com.ncvt.speed.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class OperationFileServiceImpl implements OperationFileService {

    @Value("${file-save-path}")
    private String path;

    @Resource
    private FileMapper fileMapper;

    @Resource
    private BelongMapper belongMapper;

    @Resource
    private DeleteMapper deleteMapper;

    // 重命名
    @Override
    public Result rename(String userId, RenameParams param) {
        try {
            String o = param.getBelong()+param.getOldName();
            String n = param.getBelong()+param.getNewName();
            File oldFile = new File(path,o);
            File newFile = new File(path,n);
            boolean result = oldFile.renameTo(newFile);
            FileEntity fileEntity = fileMapper.queryFileByName(param.getFileId(),userId,param.getOldName());
            if (fileEntity == null) return Result.fail(404,"数据库无记录！");
            fileEntity.setFileName(param.getNewName());
            Integer result1 = fileMapper.modifyFile(fileEntity);
            Integer result2 = 99;
            if (param.getType().equals("folder")){
                BelongEntity belong = belongMapper.queryBelongByBelong(o+"\\");
                if (belong != null){
                    belong.setBelong(n+"\\");
                    result2 = belongMapper.modifyBelong(belong);
                }
            }
            if (result2 == 99){
                if (result && result1 == 1) return Result.ok("修改成功！");
                return Result.fail("修改出现未知异常！");
            }else {
                if (result && result1 == 1 && result2 == 1) return Result.ok("修改成功！");
                return Result.fail("修改belong出现未知异常！");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("服务端异常！",e.getMessage());
        }
    }

    // 移动
    @Override
    public Result movement(MovementParams params){
        try {
            int result = fileMapper.movement(params.getFileId(),params.getBelongId());
            if (result == 1){
                return Result.ok("移动成功！");
            }else {
                return Result.fail("移动出现未知异常！");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("服务端异常！",e.getMessage());
        }
    }

    // 查询指定belong下
    @Override
    public Result queryFileByBelong(String userId, String belong) {
        try {
            String cbelong = belong.replace("@-.@","\\");
            log.info("queryFileByBelong:"+cbelong);
            BelongEntity belongEntity = belongMapper.queryBelongByBelong(cbelong);
            if (belongEntity == null) return Result.ok(404,"无法找到数据库记录");
            List<FileEntity> fileEntityList = fileMapper.queryFileByBelong(userId, belongEntity.getBelongId());
            if (fileEntityList.size() == 0) return Result.ok(201,"此目录下暂无文件,返回当前目录的belongId",belongEntity.getBelongId());
            for (FileEntity file : fileEntityList){
                String b1 = file.getBelong();
                String b2 = b1.replace("\\","@-.@");
                b2 +=file.getFileName()+"@-.@";
                file.setCBelong(b2);
            }
            return Result.ok("查询成功！",fileEntityList);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("服务端异常！",e.getMessage());
        }
    }

    // 查询回收站
    @Override
    public Result queryRecycler(String userId) {
        try {
            List<FileEntity> list = fileMapper.queryD(userId);
            if (list.size() == 0) return Result.ok(404,"数据库无记录！");
            Long nTime = System.currentTimeMillis();  //获取当前时间戳
            for (FileEntity entity : list){
                Long dTime = entity.getDelete_time();
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String sd1 = sdf1.format(new Date(Long.parseLong(String.valueOf(dTime))));
                entity.setTime(sd1);

                Long l = nTime - dTime;
                SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
                String sd2 = sdf2.format(new Date(Long.parseLong(String.valueOf(l))));
                entity.setSurplusTime(sd2);

            }
            return Result.ok("查询成功！",list);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("服务端异常！",e.getMessage());
        }
    }

    // 加入回收站
    @Override
    public Result addRecycler(String userId, RecyclerParams params) {
        try {
            boolean b = params.getType().equals("folder");
            log.info("是否文件夹："+b);
            if (params.getType().equals("folder")){

            }
            Long timeStamp = System.currentTimeMillis();  //获取当前时间戳
            DeleteEntity deleteEntity = new DeleteEntity();
            deleteEntity.setUserId(userId);
            deleteEntity.setFileId(params.getFileId());
            deleteEntity.setDeleteTime(timeStamp);
            int dResult = deleteMapper.addDelete(deleteEntity);
            int fResult = fileMapper.logicalDeletionFile(params.getFileId(), deleteEntity.getDeleteId());
            if (dResult == 1 && fResult == 1) {
                return Result.ok("添加回收站成功！");
            }else {
                return Result.fail(400,"添加回收站出现未知异常！");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("服务端异常！",e.getMessage());
        }
    }

    // 回收站还原
    @Override
    public Result restores(String fileId){
        try {
            int fResult = fileMapper.logicalDeletionFile(fileId,0L);
            int dResult = deleteMapper.dDelete(fileId);
            if (fResult == 1 && dResult == 1){
                return Result.ok("还原成功！");
            }else {
                return Result.fail(400,"还原过程出现未知异常！");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("服务端异常！",e.getMessage());
        }
    }
}
