package com.ncvt.speed.controller;


import com.ncvt.speed.params.DownloadParams;
import com.ncvt.speed.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;

@Api(tags = "下载模块")
@RestController
@Slf4j
public class DownloadController {

    @Value("${file-save-path}")
    private String path;

    private static final String utf8 = "utf-8";

    @ApiOperation(value = "下载")
    @GetMapping("/download")
    public Result endUpload(@RequestBody DownloadParams downloadParams, HttpServletRequest req, HttpServletResponse res){
        res.setCharacterEncoding(utf8);
        File file = new File(path+downloadParams.getId(),downloadParams.getFile());
        log.info("file:"+file);
        if (!file.exists()) return Result.fail(404,"文件不存在!");
        try(
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            BufferedOutputStream bos = new BufferedOutputStream(res.getOutputStream());
        ) {
            res.reset();
            res.setContentType("application/x-download");
            String fileName = URLEncoder.encode(file.getName(),utf8);
            res.addHeader("Content-Disposition","attachment;filename=" + fileName);
            byte[] b = new byte[1024 * 1024 * 10];
            int len = 0;
            while ((len = bis.read(b)) != -1){
                bos.write(b, 0, len);
                bos.close();
            }
//            bis.close();
            String url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/api/"+ downloadParams.getFile();
            return Result.ok("获取成功！",url);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("服务端出现异常！",e.getMessage());
        }
    }

}
