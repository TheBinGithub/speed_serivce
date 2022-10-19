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

@Api(tags = "下载模块")
@Controller
@Slf4j
public class DownloadController {

    @Resource
    private DownloadService downloadService;

    @ApiOperation(value = "下载(返回文件流)")
    @GetMapping("/download/{id}/{filePath}")
    @JsonBackReference
    public void downloadByFile(@PathVariable String id, @PathVariable String filePath,HttpServletRequest req, HttpServletResponse res){
        String s = filePath.replace("@", "\\");
        log.info("download... "+s);
        downloadService.downloadByFile(id, s, req, res);
    }

    @ApiOperation(value = "下载(返回地址)")
    @GetMapping("/downloads/{id}/{fileName}")
    @JsonBackReference
    public Result downloadByUrl(@PathVariable String id, @PathVariable String fileName,HttpServletRequest req, HttpServletResponse res){
        log.info("download... "+fileName);
        return downloadService.downloadByUrl(id, fileName, req, res);
    }

}
