package com.ncvt.speed.controller;

import com.ncvt.speed.service.AccountService;
import com.ncvt.speed.util.Result;
import com.ncvt.speed.mapper.AccountMapper;
import com.ncvt.speed.params.AccountParams;
import com.ncvt.speed.entity.AccountEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "登录模块")
@RestController
public class LoginController {

    @Resource
    private AccountService accountService;

    // 登录
    @ApiOperation("登录")
    @GetMapping("/login/{userName}/{password}")
    public Object login(@PathVariable String userName,@PathVariable String password){
        try {

            return accountService.login(userName,password);

        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("请求服务器错误！");
        }
    }

    // 注册
    @ApiOperation("注册")
    @PostMapping("/login")
    public Object accountAddition(@RequestBody AccountParams accountParams){

        try {
            return accountService.accountAddition(accountParams);


        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("服务器错误！");
        }
    }
//
//    // 修改密码
//    @ApiOperation("修改密码")
//    @PatchMapping("login/{id}")
//    public Result accountUpdate(@PathVariable int id, @RequestBody String password){
//
//        try {
//
//            return null;
//
//        }catch (Exception e){
//            e.printStackTrace();
//            return Result.fail("服务器错误！");
//        }
//    }
//
//    // 注销
//    @ApiOperation("注销")
//    @DeleteMapping("login/{id}")
//    public Result accountDeletion(@PathVariable int id){
//
//        return null;
//    }





}
