package com.ncvt.speed.controller;

import com.ncvt.speed.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "收藏模块")
@RestController
@Slf4j
public class CollectController {

    @ApiOperation(value = "添加收藏")
    @GetMapping("/collect/{userId}/{fileId}")
    public Result addCollect(@PathVariable String userId, @PathVariable String fileId){
        log.info("addCollect: " + fileId);
        return null;
    }

}
