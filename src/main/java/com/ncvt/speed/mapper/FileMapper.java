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
    FileEntity queryFileByName(String userId, String fileName);

    // 根据id和belong查询
    List<FileEntity> queryFileByBelong(String userId, String belong);

    // 修改记录
    Integer modifyFile(FileEntity fileEntity);

    // 添加
    Integer addFile(FileEntity file);

    // 删除
    Integer deleteFile(String FileName);

    // 修改
    Integer updateFile(FileEntity file);


}
