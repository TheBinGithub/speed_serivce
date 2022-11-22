package com.ncvt.speed.controller;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ncvt.speed.params.DownloadParams;
import com.ncvt.speed.service.DownloadService;
import com.ncvt.speed.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

@Api(tags = "下载模块")
@Controller
@Slf4j
public class DownloadController {

    @Resource
    private DownloadService downloadService;

    String separator = File.separator;  // 获取文件名称分隔符, win \ ,linux/

    @ApiOperation(value = "下载(返回文件流)")
    @GetMapping("/download/{id}/{filePath}")
    @JsonBackReference
    public void downloadByFile(@PathVariable String id, @PathVariable String filePath, HttpServletRequest req, HttpServletResponse res){
        String s = filePath.replace("@-.@", "\\");
        log.info("download " + s + "...");
        downloadService.downloadByFile(id, s, req, res);
    }

    @ApiOperation(value = "下载(返回地址)")
    @GetMapping("/downloads/{userId}/{filePath}")
    @ResponseBody
    public Result downloadByUrl(@PathVariable String userId, @PathVariable String filePath,HttpServletRequest req, HttpServletResponse res){
        String s = filePath.replace("@-.@", separator);
        return downloadService.downloadByUrl(userId, s, req, res);
    }

    @ApiOperation(value = "批量下载(返回地址)")
    @PostMapping(value="/downloads/{id}")
    @ResponseBody
    public Result batchDownloadByUrl(@PathVariable String id, @RequestBody DownloadParams downloadParams, HttpServletRequest req, HttpServletResponse res){
        log.info("download " + downloadParams.getPathList() + " ...");
        return downloadService.batchDownloadByUrl(id, downloadParams, req, res);
    }

}
