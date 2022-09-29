package com.ncvt.speed.controller;

import com.ncvt.speed.mapper.AccountMapper;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    private AccountMapper accountMapper;




}
