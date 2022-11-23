package com.ncvt.speed.service.impl;

import com.ncvt.speed.params.DownloadParams;
import com.ncvt.speed.service.DownloadService;
import com.ncvt.speed.util.Result;
import com.ncvt.speed.util.SavePath;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class DownloadServiceImpl implements DownloadService {

    private final String path = SavePath.savePath();

    private static final String utf8 = "utf-8";

    private final String separator = File.separator;

    // 下载,file
    @Override
    public void downloadByFile(String id, String filePath, HttpServletRequest req, HttpServletResponse res) {
        res.setCharacterEncoding(utf8);
        File file = new File(filePath);
        if (!file.exists()) {
            log.info("文件不存在!");
            return;
        }
        try(
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
                BufferedOutputStream bos = new BufferedOutputStream(res.getOutputStream());
        ) {
            res.reset();
            res.setContentType("application/octet-stream");
            res.addHeader("Access-Control-Allow-Origin", req.getHeader("origin"));
            res.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
            res.addHeader("Access-Control-Allow-Headers", "Content-Type");
            res.addHeader("Access-Control-Allow-Credentials","true");

            String fileName1 = URLEncoder.encode(file.getName(),utf8);
            res.addHeader("Content-Disposition","attachment;filename=" + fileName1);
            byte[] b = new byte[1024 * 1024 * 8];
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

    // 下载,url
    @Override
    public Result downloadByUrl(String id, String filePath, HttpServletRequest req, HttpServletResponse res) {
        try {
            res.reset();
            res.setContentType("application/octet-stream");
            res.addHeader("Access-Control-Allow-Origin", req.getHeader("origin"));
            res.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
            res.addHeader("Access-Control-Allow-Headers", "Content-Type");
            res.addHeader("Access-Control-Allow-Credentials","true");

            File file = new File(path,filePath);
            log.info("download " + file.getPath() + " ...");
//            res.addHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(file.getName(),utf8));
            if (!file.exists()) return Result.ok(404,"文件不存在！");
            String url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/api/file/" + filePath;
            Map<String,Object> uMap = new HashMap<>();
            uMap.put("url",url);
            String[] type = filePath.split("\\.");
            uMap.put("type",type[type.length - 1]);
            return Result.ok("获取成功！",uMap);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("服务端异常！",e.getMessage());
        }
    }

    // 批量下载,url
    @Override
    public Result batchDownloadByUrl(String userId, DownloadParams downloadParams, HttpServletRequest req, HttpServletResponse res) {
        try {
            res.reset();
            res.setContentType("application/octet-stream");
            res.addHeader("Access-Control-Allow-Origin", req.getHeader("origin"));
            res.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
            res.addHeader("Access-Control-Allow-Headers", "Content-Type");
            res.addHeader("Access-Control-Allow-Credentials","true");
            List<Map<String,Object>> lists = new ArrayList<>();
            for (List s : downloadParams.getPathList()){
                String filePath = ((String) s.get(0)).replace("@-.@",separator);
                File file = new File(path, filePath);
                if (!file.exists()) return Result.ok(404,"文件不存在！");
                String url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/api/file/" + filePath;
                Map<String,Object> uMap = new HashMap<>();
                uMap.put("url",url);
                uMap.put("filetype",s.get(1));
                uMap.put("fileName",s.get(2));
                lists.add(uMap);
            }
            return Result.ok("获取成功！",lists);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("服务端异常！",e.getMessage());
        }
    }

    // pass
    @Override
    public String downloadByUrls(String id, String fileName, HttpServletRequest req, HttpServletResponse res) {
        try {
            res.reset();
            res.setContentType("application/octet-stream");
            res.addHeader("Access-Control-Allow-Origin", req.getHeader("origin"));
            res.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
            res.addHeader("Access-Control-Allow-Headers", "Content-Type");
            res.addHeader("Access-Control-Allow-Credentials","true");

            File file = new File(path+id,fileName);
            log.info("download " + file.getPath() + " ...");
            res.addHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(file.getName(),utf8));
            if (!file.exists()) return "文件不存在！";
            String url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/api/file/" + file.getAbsolutePath();
            log.info(url);
            return url;
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }

}
