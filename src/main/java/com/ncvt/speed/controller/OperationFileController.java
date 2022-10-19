package com.ncvt.speed.controller;

import com.ncvt.speed.service.FileService;
import com.ncvt.speed.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "文件操作模块")
@RestController
@Slf4j
public class OperationFileController {

    @Resource
    private FileService fileService;

    @ApiOperation(value = "查询用户文件")
    @GetMapping("/file/{id}")
    private Result queryFileByUserId(@PathVariable String id){
        log.info("queryFileByUserId: " + id);
        return fileService.queryFileByUserId(id);
    }

}
