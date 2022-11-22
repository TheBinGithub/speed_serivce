package com.ncvt.speed.controller;

import com.ncvt.speed.params.CollectParams;
import com.ncvt.speed.service.CollectService;
import com.ncvt.speed.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "收藏模块")
@RestController
@Slf4j
public class CollectController {

    @Resource
    private CollectService collectService;

    @ApiOperation(value = "添加收藏")
    @PostMapping("/collect/{userId}")
    public Result addCollect(@PathVariable String userId, @RequestBody CollectParams collectParams){
        log.info("addCollect: " + collectParams.getCollectList());
        return collectService.addCollect(userId, collectParams.getCollectList());
    }

    @ApiOperation(value = "查询收藏")
    @GetMapping("/collect/{userId}")
    public Result queryCollect(@PathVariable String userId){
        log.info("queryCollect: " + userId);
        return collectService.qeuryCollect(userId);
    }

}
