package com.ncvt.speed.controller;

import com.ncvt.speed.params.CompletelyDeleteParams;
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
        log.info("addRecycler:"+userId+","+params.getRecyclerList());
        return operationFileService.addRecycler(userId, params.getRecyclerList());
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
        log.info("restores:"+params.getFileIds());
        return operationFileService.restores(params.getFileIds());
    }

    @ApiOperation(value = "彻底删除")
    @DeleteMapping("/recycler/{userId}")
    public Result deleteRestores(@PathVariable String userId, @RequestBody CompletelyDeleteParams params){
        log.info("deleteRestores f:"+params.getFList());
        log.info("deleteRestores d:"+params.getDList());
        log.info("deleteRestores b:"+params.getBList());
        return operationFileService.deleteRestores(userId,params);
    }

}
