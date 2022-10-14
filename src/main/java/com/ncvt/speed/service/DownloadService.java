package com.ncvt.speed.service;

import com.ncvt.speed.params.DownloadParams;
import com.ncvt.speed.util.Result;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface DownloadService {

    Result download(DownloadParams downloadParams, HttpServletRequest req, HttpServletResponse res);

}
