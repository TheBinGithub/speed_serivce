package com.ncvt.speed.controller;

import com.ncvt.speed.entity.FileEntity;
import com.ncvt.speed.service.FileService;
import com.ncvt.speed.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@Api(tags = "文件操作模块")
@RestController
@Slf4j
public class OperationFileController {

    @Value("${file-save-path}")
    private String path;

    @Resource
    private FileService fileService;

    @ApiOperation(value = "查询用户文件")
    @GetMapping("/file/{id}")
    private Result queryFileByUserId(@PathVariable String id){
        log.info("queryFileByUserId: " + id);
        return fileService.queryFileByUserId(id);
    }

    @ApiOperation(value = "新建文件夹")
    @PostMapping("/folder/{id}")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "belong", required = true),
        @ApiImplicitParam(name = "fileName", required = true)
    })
    private Result newFolder(@PathVariable String id, @RequestBody FileEntity fileEntity){
        log.info("newFolder: " + id);
        // 数据库添加记录
        File file = new File(path+id,fileEntity.getFileName());
        fileEntity.setUserId(id);
        fileEntity.setDuYou(false);
        fileEntity.setFileType("folder");
        fileEntity.setFilePath(file.getPath());
        fileEntity.setFileSize(0L);
        Long timeStamp = System.currentTimeMillis();  //获取当前时间戳
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH");
        String sd = sdf.format(new Date(Long.parseLong(String.valueOf(timeStamp))));      // 时间戳转换成时间
        fileEntity.setUploadTime(sd);
        return fileService.addFile(fileEntity,"新建成功！");
    }

}
