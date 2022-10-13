package com.ncvt.speed.mapper;

import com.ncvt.speed.entity.FileEntity;

import java.util.List;

public interface FileMapper {

    // 查询
    List<FileEntity> queryFile(String userId);

    // 添加
    Integer addFile(FileEntity file);

    // 删除
    Integer deleteFile(String FileName);

    // 修改
    Integer updateFile(FileEntity file);


}
