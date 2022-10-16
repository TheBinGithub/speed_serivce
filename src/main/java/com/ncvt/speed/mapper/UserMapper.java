package com.ncvt.speed.mapper;

import com.ncvt.speed.entity.UserEntity;

public interface UserMapper {

    // 查询单个
    UserEntity queryUser(String userId);

    // 添加
    Integer addUser(UserEntity user);

    // 删除
    Integer deleteUser(String userId);

    // 修改
    Integer updateUser(UserEntity user);
}
