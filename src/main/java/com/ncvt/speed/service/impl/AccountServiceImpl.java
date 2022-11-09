package com.ncvt.speed.service.impl;

import com.ncvt.speed.entity.AccountEntity;
import com.ncvt.speed.entity.BelongEntity;
import com.ncvt.speed.entity.FileEntity;
import com.ncvt.speed.mapper.AccountMapper;
import com.ncvt.speed.mapper.BelongMapper;
import com.ncvt.speed.mapper.FileMapper;
import com.ncvt.speed.params.AccountParams;
import com.ncvt.speed.service.AccountService;
import com.ncvt.speed.service.FileService;
import com.ncvt.speed.service.OperationFileService;
import com.ncvt.speed.util.Md5;
import com.ncvt.speed.util.Result;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    @Resource
    private FileMapper fileMapper;

    @Resource
    private BelongMapper belongMapper;

    // 查询单个
    @Override
    public Object queryOneAccount(String userName) {
        try {
            AccountEntity accountEntity = accountMapper.queryOneAccount(userName);
            if (accountEntity == null) return Result.fail(404,"账号不存在！");
            return Result.ok("查询成功！",accountEntity);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("服务端异常！");
        }
    }

    // 注册
    @Override
    public Object accountAddition(AccountParams accountParams) {
        try {
            if (accountParams.getUserName() ==null || accountParams.getPassword() == null) return Result.fail(400,"账号或密码不能为空");
            if(accountMapper.queryOneAccount(accountParams.getUserName()) != null) return Result.fail(400,"用户名已存在！");

            BelongEntity belong = new BelongEntity();
            belong.setBelong(accountParams.getUserId());
            int result1 = belongMapper.addBelong(belong);
            if (result1 != 1) return Result.fail("添加belong数据出现未知异常！");
            accountParams.setBelongId(belong.getBelongId()+"\\");

            String salt = UUID.randomUUID().toString().toUpperCase();
            accountParams.setSalt(salt);
            accountParams.setPassword(Md5.getMd5Password(accountParams.getPassword(),salt));
            int result = accountMapper.accountAddition(accountParams);
            if (result != 1) return Result.fail("注册过程出现未知异常！");

            return Result.ok("注册成功！",accountParams);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("服务端异常！");
        }
    }

    // 登录
    @Override
    public Object login(String userName, String password) {
        try {
            if (userName == null || password == null) return Result.fail(400,"账号或密码不能为空！");
            AccountEntity accountEntity = accountMapper.queryOneAccount(userName);
            if (accountEntity == null) return Result.fail(404,"账号不存在！");
            if (accountEntity.getPassword().equals(Md5.getMd5Password(password,accountEntity.getSalt()))) return Result.ok("登录成功！",accountEntity);
            return Result.fail(400,"密码错误！");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("服务端异常！");
        }
    }


}
