package com.ncvt.speed.service;

import com.ncvt.speed.entity.UserEntity;
import com.ncvt.speed.util.Result;


public interface UserService {

    // 查询
    Result queryUser(String userId);

    // 添加
    Result addUser(UserEntity user);

    // 删除
    Result deleteUser(String userId);

    // 修改
    Result updateUser(UserEntity user);

}
