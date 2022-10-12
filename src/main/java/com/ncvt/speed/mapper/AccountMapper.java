package com.ncvt.speed.mapper;


import com.ncvt.speed.entity.AccountEntity;
import com.ncvt.speed.params.AccountParams;

public interface AccountMapper{

    // 注册
    Integer accountAddition(AccountParams accountParams);

    // 查询单个账号
    AccountEntity queryOneAccount(String userName);


}
