package com.ncvt.speed.service;

import com.ncvt.speed.params.AccountParams;

public interface AccountService {

    Object accountAddition(AccountParams accountParams);

    Object login(String userName,String passwprd);

    Object queryOneAccount(String userName);


}
