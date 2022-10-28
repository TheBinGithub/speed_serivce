package com.ncvt.speed.service.impl;

import com.ncvt.speed.entity.BelongEntity;
import com.ncvt.speed.mapper.BelongMapper;
import com.ncvt.speed.service.BelongService;
import com.ncvt.speed.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BelongServiceImpl implements BelongService {

    @Resource
    private BelongMapper belongMapper;

    @Override
    public boolean modifyBelong(BelongEntity entity) {
      return false;
    }

}
