package com.ncvt.speed.controller;

import com.ncvt.speed.entity.FileEntity;
import com.ncvt.speed.params.UploaderParams;
import com.ncvt.speed.service.FileService;
import com.ncvt.speed.service.UploaderService;
import com.ncvt.speed.util.RecursiveDeletion;
import com.ncvt.speed.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;

@Api(tags = "上传模块")
@RestController
@Slf4j
public class UploaderController {

    @Resource
    private FileService fileService;

    @Resource
    private UploaderService uploaderService;

    @Value("${file-save-path}")
    private String path;

    @Value("${file-temp-path}")
    private String temppath;

    @ApiOperation(value = "单个上传")
    @PostMapping("/oneUpload/{id}")
    public Result oneUpload(@PathVariable String id,FileEntity fileEntity, MultipartFile Mfile, HttpServletRequest req) {
        if (Mfile == null) return Result.fail(400,"请上传文件");
        //获取文件的名字
        String originName = Mfile.getOriginalFilename();
        System.out.println("originName:" + originName);

        if (originName == null){
            return Result.fail(400,"文件名不能为空！");
        }
        //判断文件类型
//        if (!originName.endsWith(".jpg") && !originName.endsWith(".png")) {
//            return Result.fail("文件格式不正确");
//        }

        File pathFile = new File(path+id);
        if (!pathFile.exists()){
            pathFile.mkdirs();
        }
        File file = new File(path+id,originName);
        log.info(file.getPath());

        try {
//            req.setCharacterEncoding(utf8);

            // 此处Mfile转为File, 好像速度都差不多？
//            long s1 = System.currentTimeMillis();
//            FileUtils.copyInputStreamToFile(Mfile.getInputStream(),file);
//            long e1 = System.currentTimeMillis();
//            log.info("time1:"+(e1-s1)+"/ms");

            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));

            BufferedInputStream bis = new BufferedInputStream(Mfile.getInputStream());
            byte[] b = new byte[1024 * 1024 * 10];
            int len = 0;

            long s2 = System.currentTimeMillis();
            while ((len = bis.read(b)) != -1){
                bos.write(b,0,len);
            }
            long e2 = System.currentTimeMillis();
            log.info("time2:"+(e2-s2)+"/ms");

            bos.close();
            bis.close();

            // 数据库
//            fileEntity.setDuYou(true);

            return fileService.addFile(fileEntity,"");

            //生成返回给前端的url
//            String url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/api/images" + format + newName;

        } catch (Exception e) {
            return Result.fail("服务端异常",e.getMessage());
        } finally {

        }

    }

    @ApiOperation(value = "分片上传")
    @PostMapping("/upload/{id}")
    public Result upload(@PathVariable String id,FileEntity fileEntity, MultipartFile MFile, HttpServletRequest req){
        return uploaderService.upload(id, fileEntity, MFile, req);
    }

    @ApiOperation(value = "取消上传")
    @PostMapping("/endUpload/{id}")
    public Result endUpload(@PathVariable String id, @RequestBody UploaderParams uploaderParams){
        return uploaderService.endUpload(id, uploaderParams);
    }

}
