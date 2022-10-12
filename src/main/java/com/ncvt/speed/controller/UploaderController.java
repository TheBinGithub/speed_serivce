package com.ncvt.speed.controller;

import com.ncvt.speed.params.UploaderParams;
import com.ncvt.speed.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

@Api(tags = "上传模块")
@RestController
public class UploaderController {

    @Value("file-save-path")
    private String path;

    @ApiOperation(value = "上传")
    @PostMapping("/upload/{id}")
    public Result upload(@PathVariable String id,UploaderParams uploaderParams, MultipartFile Mfile, HttpServletRequest req) {
        if (Mfile == null) return Result.fail("请上传文件");
        //获取文件的名字
        String originName = Mfile.getOriginalFilename();
        System.out.println("originName:" + originName);

        //判断文件类型
//        if (!originName.endsWith(".jpg") && !originName.endsWith(".png")) {
//            return Result.fail("文件格式不正确");
//        }

        File file = new File(path+id+originName);
        try {
            // 此处Mfile转为File
//            FileUtils.copyInputStreamToFile(Mfile.getInputStream(),file);

            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            BufferedInputStream bis = new BufferedInputStream(Mfile.getInputStream());
            byte[] b = new byte[1024 * 1024 * 20];
            int len = 0;
            while ((len = bis.read(b)) != -1){
                bos.write(len);
            }

            bos.close();
            bis.close();

            //生成返回给前端的url
//            String url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/api/images" + format + newName;
            return Result.ok("上传成功！","文件路径：E:\\bishe\\file\\{用户id}\\文件名");

        } catch (Exception e) {
            return Result.fail(e.getMessage());
        }

    }
}
