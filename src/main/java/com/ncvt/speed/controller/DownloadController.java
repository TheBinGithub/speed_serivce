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

    @ApiOperation(value = "下载")
    @GetMapping("/download/{id}/{filePath}")
    @JsonBackReference
    public void download(@PathVariable String id, @PathVariable String filePath,HttpServletRequest req, HttpServletResponse res){
        String s = filePath.replace("@", "\\");
        log.info("download... "+s);
        downloadService.download(id, s, req, res);
    }

}
