package com.ncvt.speed.service;

import com.ncvt.speed.params.MovementParams;
import com.ncvt.speed.params.RecyclerJson;
import com.ncvt.speed.params.RecyclerParams;
import com.ncvt.speed.params.RenameParams;
import com.ncvt.speed.util.Result;

import java.util.List;

public interface OperationFileService {

    // 重命名
    Result rename(String id, RenameParams param);

    // 移动
    Result movement(MovementParams params);

    // 新建文件夹
    Result addFolder(String userId, String folderName, String belongId);

    // 根据id和belong查询
    Result queryFileByBelong(String userId, String belong);

    // 查询回收站
    Result queryRecycler(String userId);

    // 加入回收站
    Result addRecycler(String userId, List<RecyclerJson> params);

    // 回收站还原
    Result restores(List<String> fileId);

}
