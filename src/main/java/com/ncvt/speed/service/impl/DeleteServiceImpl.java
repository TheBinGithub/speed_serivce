package com.ncvt.speed.service.impl;

import com.ncvt.speed.service.DeleteService;
import com.ncvt.speed.util.Result;
import org.springframework.stereotype.Service;


@Service
public class DeleteServiceImpl implements DeleteService {

    @Override
    public Result queryRecycler(String userId) {
        return Result.ok("测试");
    }

}
