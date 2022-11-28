package com.ncvt.speed.controller;

import com.ncvt.speed.params.NicknameParams;
import com.ncvt.speed.params.PasswordParams;
import com.ncvt.speed.params.SePasswordParams;
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

    @ApiOperation(value = "修改昵称")
    @PutMapping("/name/{userId}")
    public Result updateUserByName(@PathVariable String userId, @RequestBody NicknameParams params){
        log.info("updateUserByName: " + userId);
        return userService.updateNickname(userId, params.getNickname());
    }

    @ApiOperation(value = "校验二级密码")
    @GetMapping("/sePassword/{userId}/{sePassword}")
    public Result CheckSePassword(@PathVariable String userId, @PathVariable String sePassword){
        log.info("CheckSePassword: " + userId);
        return userService.CheckSePassword(sePassword, userId);
    }

    @ApiOperation(value = "设置二级密码")
    @PostMapping("/sePassword/{userId}")
    public Result addUserBysePassword(@PathVariable String userId, @RequestBody SePasswordParams params){
        log.info("addUserBysePassword: " + userId);
        return userService.addSepasswrod(params.getSePassword(), userId);
    }

    @ApiOperation(value = "修改登录密码")
    @PutMapping("/password/{userId}")
    public Result updateUserByPassword(@PathVariable String userId, @RequestBody PasswordParams params){
        log.info("updateUserByPassword: " + userId);
        return userService.updatePassword(params.getOldPassword(), params.getNewPassword(), userId);
    }

    @ApiOperation(value = "修改二级密码")
    @PutMapping("/sePassword/{userId}")
    public Result updateUserBySePassword(@PathVariable String userId, @RequestBody PasswordParams params){
        log.info("updateUserBySePassword: " + userId);
        return userService.updateSecondPassword(params.getOldPassword(), params.getNewPassword(), userId);
    }

}
