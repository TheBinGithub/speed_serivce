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
        @ApiImplicitParam(name = "shunk", required = true),
        @ApiImplicitParam(name = "shunks", required = true),
        @ApiImplicitParam(name = "fileName", required = true),
        @ApiImplicitParam(name = "MFile", required = true),
        @ApiImplicitParam(name = "belong", required = true),
        @ApiImplicitParam(name = "way", required = true)
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
