package com.ncvt.speed.controller;

import com.ncvt.speed.entity.FileEntity;
import com.ncvt.speed.params.UploaderParams;
import com.ncvt.speed.service.FileService;
import com.ncvt.speed.service.UploaderService;
import com.ncvt.speed.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Api(tags = "上传模块")
@RestController
@Slf4j
public class UploaderController {

    @Resource
    private UploaderService uploaderService;

    @Resource
    private FileService fileService;

    @ApiOperation(value = "分片上传")
    @PostMapping("/upload/{id}")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "shunk", required = true, value = "当前分片数"),
        @ApiImplicitParam(name = "shunks", required = true, value = "分片总大小"),
        @ApiImplicitParam(name = "fileName", required = true, value = "文件名"),
        @ApiImplicitParam(name = "MFile", required = true, value = "文件"),
        @ApiImplicitParam(name = "belongId", required = true, value = "属于id"),
        @ApiImplicitParam(name = "way", required = true, value = "上传方式(0切100片上传,1秒传,2每片切10m)"),
        @ApiImplicitParam(name = "hash", required = true, value = "哈希值")
    })
    public Result upload(@PathVariable String id,FileEntity fileEntity, MultipartFile MFile, HttpServletRequest req){
        // 此处MFile转为File, 好像速度都差不多？
//            long s1 = System.currentTimeMillis();
//            FileUtils.copyInputStreamToFile(MFile.getInputStream(),file);
//            long e1 = System.currentTimeMillis();
//            log.info("time1:"+(e1-s1)+"/ms");
        return uploaderService.upload(id, fileEntity, MFile, req);
    }

    @ApiOperation(value = "取消上传")
    @PostMapping("/endUpload/{id}")
    public Result endUpload(@PathVariable String id, @RequestBody UploaderParams uploaderParams){
        return uploaderService.endUpload(id, uploaderParams);
    }

    @ApiOperation(value = "hast校验")
    @GetMapping("/hash/{userId}/{hash}")
    public Result hashCheck(@PathVariable String userId, @PathVariable String hash){
        return fileService.hashCheck(userId, hash);
    }

}
