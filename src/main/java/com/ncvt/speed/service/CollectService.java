package com.ncvt.speed.service;

import com.ncvt.speed.util.Result;

import java.util.List;

public interface CollectService {

    // 添加收藏
    Result addCollect(String userId, List<String> fileIdList);

    // 查询收藏
    Result qeuryCollect(String userId);

}
