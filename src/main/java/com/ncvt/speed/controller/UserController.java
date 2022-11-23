package com.ncvt.speed.controller;


import com.ncvt.speed.entity.UserEntity;
import com.ncvt.speed.service.UserService;
import com.ncvt.speed.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "个人信息模块")
@RestController
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation(value = "查询个人信息")
    @GetMapping("/user/{userId}")
    public Result queryUser(@PathVariable String userId){
        log.info("queryUser: " + userId);
        return userService.queryUser(userId);
    }

    @ApiOperation(value = "修改用户名和昵称")
    @PutMapping("/name/{userId}")
    public Result updateUserByName(@PathVariable String userId, @RequestBody String nickname, @RequestBody String userName){
        log.info("updateUserByName: " + userId);
        return userService.updateNickname(userId, nickname, userName);
    }

    @ApiOperation(value = "校验二级密码")
    @GetMapping("/sePassword/{userId}")
    public Result CheckSePassword(@PathVariable String userId, @RequestBody String sePassword){
        log.info("CheckSePassword: " + userId);
        return userService.CheckSePassword(sePassword, userId);
    }

    @ApiOperation(value = "设置二级密码")
    @PostMapping("/sePassword/{userId}")
    public Result addUserBysePassword(@PathVariable String userId, @RequestBody String sePassword){
        log.info("addUserBysePassword: " + userId);
        return userService.addSepasswrod(sePassword, userId);
    }

    @ApiOperation(value = "修改登录密码")
    @PutMapping("/Password/{userId}")
    public Result updateUserByPassword(@PathVariable String userId, @RequestBody String oldPassword, @RequestBody String newPassword){
        log.info("updateUserByPassword: " + userId);
        return userService.updatePassword(oldPassword, newPassword, userId);
    }

    @ApiOperation(value = "修改二级密码")
    @PutMapping("/sePassword/{userId}")
    public Result updateUserBySePassword(@PathVariable String userId, @RequestBody String oldPassword, @RequestBody String newPassword){
        log.info("updateUserBySePassword: " + userId);
        return userService.updateSecondPassword(oldPassword, newPassword, userId);
    }

}
