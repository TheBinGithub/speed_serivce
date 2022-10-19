package com.ncvt.speed.service;

import com.ncvt.speed.entity.FileEntity;
import com.ncvt.speed.params.DownloadParams;
import com.ncvt.speed.util.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface DownloadService {

    void downloadByFile(String id, String filePath, FileEntity fileEntity, HttpServletRequest req, HttpServletResponse res);

    Result downloadByUrl(String id, String fileName, HttpServletRequest req, HttpServletResponse res);

}
