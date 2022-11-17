package com.ncvt.speed.controller;

import com.ncvt.speed.params.ShareParams;
import com.ncvt.speed.service.ShareService;
import com.ncvt.speed.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Api(tags = "分享模块")
@RestController
public class ShareController {

    @Resource
    private ShareService shareService;

    @ApiOperation(value = "添加分享")
    @PostMapping("/share/{userId}")
    public Result addShare(@PathVariable String userId, @RequestBody ShareParams shareParams, HttpServletRequest req){
        return shareService.addShare(userId, shareParams, req);
    }

}
