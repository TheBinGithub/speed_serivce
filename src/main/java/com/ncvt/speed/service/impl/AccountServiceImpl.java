package com.ncvt.speed.service.impl;

import com.ncvt.speed.entity.AccountEntity;
import com.ncvt.speed.mapper.AccountMapper;
import com.ncvt.speed.params.AccountParams;
import com.ncvt.speed.service.AccountService;
import com.ncvt.speed.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    @Override
    public Object queryOneAccount(String userName) {
        try {
            return Result.ok("查询成功！",accountMapper.queryOneAccount(userName));

        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("服务端异常！");
        }
    }

    @Override
    public Object accountAddition(AccountParams accountParams) {

        try {

            if (accountParams.getUserName() ==null || accountParams.getPassword() == null){
                return Result.fail(400,"账号或密码不能为空");
            }

            if(accountMapper.queryOneAccount(accountParams.getUserName()) != null){
                return Result.fail(400,"用户名已存在！");
            }

            Integer result = accountMapper.accountAddition(accountParams);
            if (result != 1){
                return Result.fail("注册过程出现未知异常！");
            }

            return Result.ok("注册成功！",accountParams);


        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("服务端异常！");
        }
    }

    @Override
    public Object login(String userName, String passwprd) {

        try {
            if (userName == null || passwprd == null){
                return Result.fail(400,"账号或密码不能为空！");
            }


            return null;


        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("服务端异常！");
        }
    }


}
