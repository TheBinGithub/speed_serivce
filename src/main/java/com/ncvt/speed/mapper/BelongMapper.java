package com.ncvt.speed.mapper;

import com.ncvt.speed.entity.BelongEntity;

public interface BelongMapper {

    // 新增
    Integer addBelong(BelongEntity entity);

    // 根据id查询
    BelongEntity queryBelongById(Integer belongId);

    // 根据belong查询
    BelongEntity queryBelongByBelong(String beong);

    // 修改
    Integer modifyBelong(BelongEntity entity);

    // 删除
    Integer deleteBelong(Integer belongId);

}
