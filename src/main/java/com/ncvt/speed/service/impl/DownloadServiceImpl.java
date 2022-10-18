package com.ncvt.speed.service.impl;

import com.ncvt.speed.service.DownloadService;
import com.ncvt.speed.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;

@Service
@Slf4j
public class DownloadServiceImpl implements DownloadService {

    @Value("${file-save-path}")
    private String path;

    private static final String utf8 = "utf-8";

    // 下载
    @Override
    public void download(String id, String filePath, HttpServletRequest req, HttpServletResponse res) {
        res.setCharacterEncoding(utf8);
        File file = new File(filePath);
        if (!file.exists()) log.info("文件不存在!");
        try(
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
                BufferedOutputStream bos = new BufferedOutputStream(res.getOutputStream());
        ) {
            res.reset();
            res.setContentType("application/x-download");

            res.addHeader("Access-Control-Allow-Origin", req.getHeader("origin"));
            res.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
            res.addHeader("Access-Control-Allow-Headers", "Content-Type");
            res.addHeader("Access-Control-Allow-Credentials","true");

            String fileName1 = URLEncoder.encode(file.getName(),utf8);
            res.addHeader("Content-Disposition","attachment;filename=" + fileName1);
            byte[] b = new byte[1024 * 1024 * 10];
            int len = 0;
            while ((len = bis.read(b)) != -1){
                bos.write(b, 0, len);
                bos.flush();
            }
//            bis.close();
//            String url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/api/" + filePath;
        }catch (Exception e){
            e.printStackTrace();
//            return Result.fail("服务端出现异常！",e.getMessage());
        }
    }

}
