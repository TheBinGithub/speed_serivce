package com.ncvt.speed.service.impl;

import com.ncvt.speed.entity.BelongEntity;
import com.ncvt.speed.entity.DeleteEntity;
import com.ncvt.speed.entity.FileEntity;
import com.ncvt.speed.mapper.BelongMapper;
import com.ncvt.speed.mapper.DeleteMapper;
import com.ncvt.speed.mapper.FileMapper;
import com.ncvt.speed.params.MovementJson;
import com.ncvt.speed.params.MovementParams;
import com.ncvt.speed.params.RecyclerJson;
import com.ncvt.speed.params.RenameParams;
import com.ncvt.speed.service.OperationFileService;
import com.ncvt.speed.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class OperationFileServiceImpl implements OperationFileService {

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
            FileEntity fileEntity = new FileEntity();
            fileEntity.setFileId(param.getFileId());
            fileEntity.setFileName(param.getNewName());
            int result = fileMapper.modifyFile(fileEntity);
            if (param.getType().equals("folder")){
                BelongEntity belong = new BelongEntity();
                belong.setBelongId(param.getFolderBelongId());
                belong.setBelong(param.getNewName());
                int result1 = belongMapper.modifyBelong(belong);
                if (result1 == 1 && result == 1) return Result.ok("文件夹重命名成功！");
                return Result.fail("文件夹重命名出现未知异常！");
            }
            if (result == 1) return Result.ok("文件重命名成功！");
            return Result.fail("文件重命名出现未知异常！");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("服务端异常！",e.getMessage());
        }
    }

    // 移动
//    @Override
//    public Result movement(MovementParams params){
//        try {
//            if (params.getOldBelongId().equals(params.getNewBelongId())) return Result.fail(300,"无效文件移动！");
//            if (params.getType().equals("folder")){
//                String cBelong = (params.getOldBelongId()+params.getFolderBelongId()+"@-.@");
//                List<FileEntity> lists = fileMapper.queryFileLikeBelongId(cBelong);
//                int result = -1;
//                if (lists.size() != 0){
//                    for (FileEntity list : lists){
//                        if (params.getOldBelongId().equals("")) {
//                            list.setBelongId(params.getNewBelongId()+list.getBelongId());
//                        }else {
//                            list.setBelongId(list.getBelongId().replace(params.getOldBelongId(),params.getNewBelongId()));
//                        }
//                    }
//                    result = fileMapper.movementFolder(lists);
//                }
//                int result1 = fileMapper.movement(params.getFileId(),params.getNewBelongId());
//                if (result == 0 && result1 != 1) return Result.fail("移动文件夹出现未知异常！");
//                return Result.ok("移动文件夹成功！");
//            }
//            int result = fileMapper.movement(params.getFileId(),params.getNewBelongId());
//            if (result != 1) return Result.fail("移动文件出现未知异常！");
//            return Result.ok("移动文件成功！");
//        }catch (Exception e){
//            e.printStackTrace();
//            return Result.fail("服务端异常！",e.getMessage());
//        }
//    }

    // 移动
    @Override
    public Result movement(MovementParams params){
        try {
            if (params.getMovementList().get(0).getOldBelongId().equals(params.getNewBelongId())) return Result.fail(300,"无效文件移动！");
            List<FileEntity> fList = new ArrayList<>();
            for (MovementJson m : params.getMovementList()){
                FileEntity file = new FileEntity();
                file.setFileId(m.getFileId());
                file.setBelongId(params.getNewBelongId());
                fList.add(file);
                if (m.getType().equals("folder")){
                    String cBelong = (m.getOldBelongId()+m.getFolderBelongId()+"@-.@");
                    List<FileEntity> lists = fileMapper.queryFileLikeBelongId(cBelong);
                    if (lists.size() != 0){
                        for (FileEntity list : lists){
                            if (m.getOldBelongId().equals("")) {
                                list.setBelongId(params.getNewBelongId()+list.getBelongId());
                            }else {
                                list.setBelongId(list.getBelongId().replace(m.getOldBelongId(),params.getNewBelongId()));
                            }
                        }
                    }
                    fList.addAll(lists);
                }
            }
            int result = fileMapper.movement(fList);
            if (result != fList.size()) return Result.fail("移动出现未知异常！");
            return Result.ok("移动成功！");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("服务端异常！",e.getMessage());
        }
    }

    // 新建文件夹
    @Override
    public Result addFolder(String userId, String folderName, String belongId) {
        try {

            List<FileEntity> f = fileMapper.queryFileByFileName(belongId,folderName);
            if (f.size() != 0) return Result.fail(400,"当前目录已存在");

            FileEntity fileEntity = new FileEntity();
            fileEntity.setFileName(folderName);
            fileEntity.setUserId(userId);
            fileEntity.setDuYou(0);
            fileEntity.setFileType("folder");
            fileEntity.setFileSize(0L);
            fileEntity.setDeleteId(0);
            Long timeStamp = System.currentTimeMillis();  //获取当前时间戳
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:ss");
            String sd = sdf.format(new Date(Long.parseLong(String.valueOf(timeStamp))));  // 时间戳转换成时间
            fileEntity.setUploadTime(sd);

            BelongEntity b = new BelongEntity();
            b.setBelong(folderName);
            int result = belongMapper.addBelong(b);

            fileEntity.setFolderBelongId(b.getBelongId());
            fileEntity.setBelongId(belongId);
            if (result != 1) return Result.fail("belong出现未知异常！");

            int result1 = fileMapper.addFile(fileEntity);
            if (result1 != 1) return Result.fail("file出现未知异常！");

            return Result.ok("新建成功！");

        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("服务端异常！",e.getMessage());
        }
    }

    // 查询指定belong下
    @Override
    public Result queryFileByBelong(String userId, String belongId) {
        try {
            log.info("queryFileByBelong:"+belongId);
            List<FileEntity> fileEntityList = fileMapper.queryFileByBelong(userId, belongId);
//            if (fileEntityList.size() == 0) return Result.fail(404,"数据库无记录！");
            if (fileEntityList.size() == 0) return Result.ok(201,"此目录下暂无文件，返回当前文件夹的cBelong!",belongId);
            for (FileEntity file : fileEntityList){
                String b = file.getBelongId();
                b +=file.getFolderBelongId()+"@-.@";
                file.setCBelong(b);
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
    public Result addRecycler(String userId, List<RecyclerJson> lists) {
        try {
            List<DeleteEntity> list = new ArrayList<>();
            Long timeStamp = System.currentTimeMillis();  //获取当前时间戳
            for (RecyclerJson r : lists){
                DeleteEntity deleteEntity = new DeleteEntity();
                deleteEntity.setDeleteTime(timeStamp);
                deleteEntity.setUserId(userId);
                deleteEntity.setFileId(r.getFileId());
                list.add(deleteEntity);
                if (r.getType().equals("folder")) {
                    List<FileEntity> fe = fileMapper.queryFileLikeBelongId(r.getCBelongId());
                    if (fe.size() != 0){
                        for (FileEntity f : fe){
                            DeleteEntity deleteEntity1 = new DeleteEntity();
                            deleteEntity1.setDeleteTime(timeStamp);
                            deleteEntity1.setUserId(userId);
                            deleteEntity1.setFileId(f.getFileId());
                            list.add(deleteEntity1);
                        }
                    }
                }
            }
            int dResult = deleteMapper.addDeleteList(list);
            int fResult = fileMapper.modifyFileByFileId(list);
            if (dResult != fResult) return Result.fail("加入回收站出现未知异常！");
            return Result.ok("加入回收站成功！","修改了"+dResult+"条记录！");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("服务端异常！",e.getMessage());
        }
    }

    // 回收站还原
    @Override
    public Result restores(List<String> fileIds){
        try {
            int fResult = fileMapper.restoresFile(fileIds);
            int dResult = deleteMapper.restoresDelete(fileIds);
            if ((fResult + dResult) != (fileIds.size() * 2)) return Result.fail(400,"还原过程出现未知异常！");
//            int fResult = fileMapper.logicalDeletionFile(fileIds.get(0),0L);
//            int dResult = deleteMapper.dDelete(fileIds.get(0));
//            if (fResult != 1 || dResult != 1) return Result.fail(400,"还原过程出现未知异常！");
            return Result.ok("还原成功！");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("服务端异常！",e.getMessage());
        }
    }
}
