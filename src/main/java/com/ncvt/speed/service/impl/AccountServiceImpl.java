package com.ncvt.speed.service.impl;

import com.ncvt.speed.entity.AccountEntity;
import com.ncvt.speed.mapper.AccountMapper;
import com.ncvt.speed.params.AccountParams;
import com.ncvt.speed.service.AccountService;
import com.ncvt.speed.util.Result;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    // md5算法加密处理
    private String getMd5Password(String password,String salt){

        // md5加密算法,三次
        for (int i=0;i<3;i++){
            password = DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();
        }

        return password;
    }

    // 查询单个
    @Override
    public Object queryOneAccount(String userName) {
        try {
            AccountEntity accountEntity = accountMapper.queryOneAccount(userName);
            if (accountEntity == null) return Result.fail(400,"无结果！");
            return Result.ok("查询成功！",accountEntity);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("服务端异常！");
        }
    }

    // 添加
    @Override
    public Object accountAddition(AccountParams accountParams) {

        try {

            if (accountParams.getUserName() ==null || accountParams.getPassword() == null){
                return Result.fail(400,"账号或密码不能为空");
            }

            if(accountMapper.queryOneAccount(accountParams.getUserName()) != null){
                return Result.fail(400,"用户名已存在！");
            }

            String salt = UUID.randomUUID().toString().toUpperCase();
            accountParams.setSalt(salt);

            accountParams.setPassword(getMd5Password(accountParams.getPassword(),salt));

            Integer result = accountMapper.accountAddition(accountParams);
            if (result != 1) return Result.fail("注册过程出现未知异常！");

            return Result.ok("注册成功！",accountParams);

        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("服务端异常！");
        }
    }

    // 登录
    @Override
    public Object login(String userName, String passwprd) {

        try {
            if (userName == null || passwprd == null){
                return Result.fail(400,"账号或密码不能为空！");
            }
            AccountEntity accountEntity = accountMapper.queryOneAccount(userName);

            if (accountEntity == null){
                return Result.fail(400,"账号不存在！");
            }

            if (accountEntity.getPassword().equals(getMd5Password(passwprd,accountEntity.getSalt()))){
                return Result.ok("登录成功！",accountEntity);
            }
            return Result.fail(400,"密码错误！");


        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("服务端异常！");
        }
    }


}
