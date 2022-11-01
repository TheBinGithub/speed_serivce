package com.ncvt.speed.service;

import com.ncvt.speed.entity.FileEntity;
import com.ncvt.speed.util.Result;

public interface FileService {

    // 查询用户文件
    Result queryFileByUserId(String userId);

    // hash校验
    Result hashCheck(String userId, String hash);

    // 根据id和path查询
    Result queryFileByName(String fileId, String userId, String fileName);

    // 重命名
    Result modifyFile(FileEntity fileEntity);

    // 逻辑删除

    // 添加
    Result addFile(FileEntity file, String msg);

    // 删除
    Result deleteFile(String FileName);

    // 修改
    Result updateFile(FileEntity file);

}
