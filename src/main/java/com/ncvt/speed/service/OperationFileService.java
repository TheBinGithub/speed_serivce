package com.ncvt.speed.service;

import com.ncvt.speed.params.RenameParams;
import com.ncvt.speed.util.Result;

public interface OperationFileService {

    Result rename(String id, RenameParams param);

    // 根据id和belong查询
    Result queryFileByBelong(String userId, String belong);

}
