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

    @Override
    public boolean addBelon(BelongEntity entity) {
        try {
            int result = belongMapper.addBelong(entity);
            return result == 1;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
