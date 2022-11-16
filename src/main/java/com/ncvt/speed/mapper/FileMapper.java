package com.ncvt.speed.mapper;

import com.ncvt.speed.entity.DeleteEntity;
import com.ncvt.speed.entity.FileEntity;
import com.ncvt.speed.util.Result;

import java.util.List;

public interface FileMapper {

    // 查询用户文件
    List<FileEntity> queryFile(String userId);

    // 查询hash
    List<FileEntity> queryHash(String userId, String hash);

    // 根据id和fileName查询
    FileEntity queryFileByName(String fileId,String userId, String fileName);

    // 根据belongId和fileName查询
    List<FileEntity> queryFileByFileName(String belongId, String fileName);

    // 根据id和belongId查询
    List<FileEntity> queryFileByBelong(String userId, String belongId);

    // 根据belongId右侧模糊查询
    List<FileEntity> queryFileLikeBelongId(String BelongId);

    // 修改记录
    Integer modifyFile(FileEntity fileEntity);

    // 查询回收站的
    List<FileEntity> queryD(String userId);

    // 根据fileId批量修改(批量逻辑删除)
    Integer modifyFileByFileId(List<DeleteEntity> lists);

    // 恢复文件
    Integer restoresFile(List<String> fileIds);

    // 移动文件
    Integer movement(String fileId, String nBelongId);

    // 移动文件夹
    Integer movementFolder(List<FileEntity> lists);

    // 逻辑删除
    Integer logicalDeletionFile(String fileId, Long deleteId);

    // 添加
    Integer addFile(FileEntity file);

    // 删除
    Integer deleteFile(String FileName);

    // 修改
    Integer updateFile(FileEntity file);


}
