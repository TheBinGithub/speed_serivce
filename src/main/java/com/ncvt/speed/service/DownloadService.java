package com.ncvt.speed.service;

import com.ncvt.speed.params.DownloadParams;
import com.ncvt.speed.util.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface DownloadService {

    void download(String id, String filePath, HttpServletRequest req, HttpServletResponse res);

}
