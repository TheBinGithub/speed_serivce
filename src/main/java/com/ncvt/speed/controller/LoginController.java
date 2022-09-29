package com.ncvt.speed.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ncvt.speed.config.Result;
import com.ncvt.speed.mapper.AccountMapper;
import com.ncvt.speed.params.AccountParams;
import com.ncvt.speed.pojo.AccountPojo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "登录模块")
@RestController
public class LoginController {

    @Resource
    private AccountMapper accountMapper;

    // 登录
    @ApiOperation("登录")
    @GetMapping("/login/{userName}/{password}")
    public Result login(@PathVariable String userName,@PathVariable String password){
        try {
            Map<String, Object> dataMap = new HashMap<>();

            AccountPojo user = accountMapper.selectOne(Wrappers.<AccountPojo>lambdaQuery()
                .eq(AccountPojo::getUserName, userName)
                .eq(AccountPojo::getPassword, password)
            );

            if (user == null){
                return Result.fail(300,"账号或密码错误");
            }

            return Result.ok("登录成功！");

        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("请求服务器错误！");
        }
    }

    // 注册
    @ApiOperation("注册")
    @PostMapping("/login")
    public Result accountAddition(@RequestBody AccountParams accountParams){

        try {

            if (accountParams.getUserName() == null || accountParams.getPassword() == null){
                return Result.fail(300,"账号或密码不能为空！");
            }

            AccountPojo user = accountMapper.selectOne(Wrappers.<AccountPojo>lambdaQuery()
                    .eq(AccountPojo::getUserName, accountParams.getUserName())
            );

            if (user != null){
                return Result.fail(301,"用户名已存在！");
            }

            AccountPojo accountPojo = new AccountPojo();
            accountPojo.setUserName(accountParams.getUserName());
            accountPojo.setPassword(accountParams.getPassword());
            Integer result = accountMapper.insert(accountPojo);

            if (result != 1){
                return Result.fail(400,"注册过程出现未知错误！");
            }

            Map<String,Object> dataMap = new HashMap<>();
            dataMap.put("userName", accountParams.getUserName());

            return Result.ok("注册成功",dataMap);

        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("服务器错误！");
        }

    }

    // 修改密码
    @ApiOperation("修改密码")
    @PatchMapping("login/{id}/{password}")
    public Result accountUpdate(@PathVariable int id, @Validated String password){

        try {

            return null;

        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("服务器错误！");
        }
    }

    // 注销
    @ApiOperation("注销")
    @DeleteMapping("login/{id}")
    public Result accountDeletion(@PathVariable int id){

        return null;
    }





}
