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

    // 修改昵称
    Result updateNickname(String userId, String nickname);

    // 修改登录密码
    Result updatePassword(String oldPassword, String newPassword, String userId);

    // 二级密码校验
    Result CheckSePassword(String sePassword, String userId);

    // 设置二级密码
    Result addSepasswrod(String sePassword, String userId);

    // 修改二级密码
    Result updateSecondPassword(String oldPassword, String newPassword, String userId);

}
