package com.ncvt.speed.controller;

import com.ncvt.speed.params.RecyclerParams;
import com.ncvt.speed.service.OperationFileService;
import com.ncvt.speed.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
        return operationFileService.addRecycler(userId, params);
    }



}
