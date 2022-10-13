package com.ncvt.speed.service;

import com.ncvt.speed.entity.FileEntity;
import com.ncvt.speed.util.Result;
import org.springframework.stereotype.Service;

import java.util.List;

public interface FileService {


    // 查询
    Result queryFile(String userId);

    // 添加
    Result addFile(FileEntity file, String msg);

    // 删除
    Result deleteFile(String FileName);

    // 修改
    Result updateFile(FileEntity file);

}
