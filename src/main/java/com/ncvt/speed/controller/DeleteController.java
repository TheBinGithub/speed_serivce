package com.ncvt.speed.controller;

import com.ncvt.speed.params.RecyclerParams;
import com.ncvt.speed.params.RestoresParams;
import com.ncvt.speed.service.OperationFileService;
import com.ncvt.speed.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "回收站模块")
@RestController
@Slf4j
public class DeleteController {

    @Resource
    private OperationFileService operationFileService;

    @ApiOperation(value = "加入回收站")
    @PostMapping("/recycler/{userId}")
    public Result addRecycler(@PathVariable String userId, @RequestBody RecyclerParams params){
        log.info("addRecycler:"+userId+","+params.getFileId());
        return operationFileService.addRecycler(userId, params);
    }

    @ApiOperation(value = "查询回收站")
    @GetMapping("/recycler/{userId}")
    public Result queryRecycler(@PathVariable String userId){
        log.info("queryRecyclerByUserId:"+userId);
        return operationFileService.queryRecycler(userId);
    }

    @ApiOperation(value = "回收站还原")
    @PutMapping("/recycler/{userId}")
    public Result restores(@PathVariable String userId, @RequestBody RestoresParams params){
        log.info("restores:"+params.getFileId());
        return operationFileService.restores(params.getFileId());
    }




}
