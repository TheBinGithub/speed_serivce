package com.ncvt.speed.mapper;

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

    // 根据id和belong查询
    List<FileEntity> queryFileByBelong(String userId, Integer belongId);

    // 修改记录
    Integer modifyFile(FileEntity fileEntity);

    // 查询回收站的
    List<FileEntity> queryD(String userId);

    // 根据belong批量修改(批量逻辑删除)
    Integer modifyFileByBelong(List<String> list);

    // 移动
    Integer movement(String fileId, String belongId);

    // 逻辑删除
    Integer logicalDeletionFile(String fileId, Long deleteId);

    // 添加
    Integer addFile(FileEntity file);

    // 删除
    Integer deleteFile(String FileName);

    // 修改
    Integer updateFile(FileEntity file);


}
