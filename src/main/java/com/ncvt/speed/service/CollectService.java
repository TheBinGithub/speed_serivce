package com.ncvt.speed.service;

import com.ncvt.speed.params.CollectJson;
import com.ncvt.speed.util.Result;

import java.util.List;

public interface CollectService {

    // 添加收藏
    Result addCollect(String userId, List<CollectJson> collectList);

    // 查询收藏
    Result qeuryCollect(String userId);

    // 移出收藏
    Result deleteCollect(List<String> fileIdList, List<String> collectIdList);

}
