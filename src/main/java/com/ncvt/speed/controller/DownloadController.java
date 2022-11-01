package com.ncvt.speed.controller;


import com.fasterxml.jackson.annotation.JsonBackReference;
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

@Api(tags = "下载模块")
@Controller
@Slf4j
public class DownloadController {

    @Resource
    private DownloadService downloadService;

    @ApiOperation(value = "下载(返回文件流)")
    @GetMapping("/download/{id}/{filePath}")
    @JsonBackReference
    public void downloadByFile(@PathVariable String id, @PathVariable String filePath, HttpServletRequest req, HttpServletResponse res){
        String s = filePath.replace("@", "\\");
        log.info("download " + s + "...");
        downloadService.downloadByFile(id, s, req, res);
    }

    @ApiOperation(value = "下载(返回地址)")
    @GetMapping("/downloads/{id}/{fileName}")
    @ResponseBody
    public Result downloadByUrl(@PathVariable String id, @PathVariable String fileName,HttpServletRequest req, HttpServletResponse res){
        String s = fileName.replace("@-.@", "\\");
        return downloadService.downloadByUrl(id, s, req, res);
    }

    @ApiOperation(value = "下载1(返回地址1)")
//  ,produces="application/octet-stream,charset=utf-8"
    @GetMapping(value="/downloading/{id}/{fileName}")
    @ResponseBody
    public String downloadByUrls(@PathVariable String id, @PathVariable String fileName,HttpServletRequest req, HttpServletResponse res){
        String s = fileName.replace("@", "\\");
        log.info("download " + s + " ...");
        return downloadService.downloadByUrls(id, s, req, res);
    }

}
