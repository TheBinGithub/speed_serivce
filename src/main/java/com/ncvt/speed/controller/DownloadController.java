package com.ncvt.speed.controller;


import com.ncvt.speed.params.DownloadParams;
import com.ncvt.speed.service.DownloadService;
import com.ncvt.speed.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(tags = "下载模块")
@RestController
@Slf4j
public class DownloadController {

    @Resource
    private DownloadService downloadService;

    @ApiOperation(value = "下载")
    @GetMapping("/download/{id}/{filePath}")
    public Result download(@PathVariable String id, @PathVariable String filePath,HttpServletRequest req, HttpServletResponse res){
        return downloadService.download(id, filePath, req, res);
    }

}
