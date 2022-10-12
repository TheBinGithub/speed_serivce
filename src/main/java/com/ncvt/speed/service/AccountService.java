package com.ncvt.speed.service;

import com.ncvt.speed.params.AccountParams;

public interface AccountService {

    // 注册
    Object accountAddition(AccountParams accountParams);
    // 登录
    Object login(String userName,String passwprd);
    //查询单个
    Object queryOneAccount(String userName);


}
