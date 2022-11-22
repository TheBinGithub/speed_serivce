package com.ncvt.speed.service;

import com.ncvt.speed.util.Result;

import java.util.List;

public interface CollectService {

    Result addCollect(String userId, List<String> fileIdList);

}
