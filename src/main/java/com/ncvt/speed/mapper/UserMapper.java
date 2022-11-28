package com.ncvt.speed.mapper;

import com.ncvt.speed.entity.UserEntity;

public interface UserMapper {

    // 查询单个
    UserEntity queryUser(String userId);

    // 添加
    Integer addUser(UserEntity user);

    // 注销
    Integer deleteUser(String userId);

    // 昵称修改
    Integer updateNickname(String nickname, String userId);

    // 查询二级密码
    UserEntity querySecondPassword(String userId);

    // 二级密码修改
    Integer updateSecondPassword(String secondPassword, String sSalt, String userId);

    // 容量+
    Integer modifyUserBySpaceU(String userId, Long space);

    // 容量-
    Integer modifyUserBySpaceD(String userId, Long space);

}
