package com.ncvt.speed.service.impl;

import com.ncvt.speed.mapper.DeleteMapper;
import com.ncvt.speed.service.DeleteService;
import com.ncvt.speed.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DeleteServiceImpl implements DeleteService {

    @Resource
    private DeleteMapper deleteMapper;

    @Override
    public Result queryRecycler(String userId) {
        return Result.ok("测试");
    }

}
