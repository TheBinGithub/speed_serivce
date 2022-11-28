package com.ncvt.speed.mapper;

import com.ncvt.speed.entity.AccountEntity;
import com.ncvt.speed.params.AccountParams;

public interface AccountMapper{

    // 注册
    Integer accountAddition(AccountParams accountParams);

    // 查询单个账号
    AccountEntity queryOneAccount(String userName);

    // 修改用户名
    Integer updateUserName(String userName, String userId);

    // 查询登录密码
    AccountEntity queryAccountByPassword(String userId);

    // 修改登录密码
    Integer updateUserByPassword(String password, String userId);

}
