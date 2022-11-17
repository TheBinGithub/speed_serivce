package com.ncvt.speed.service;

import com.ncvt.speed.entity.FileEntity;
import com.ncvt.speed.params.UploaderParams;
import com.ncvt.speed.util.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface UploaderService {

    Result upload(String id, FileEntity fileEntity, MultipartFile MFile, HttpServletRequest req,Long s);

    Result endUpload(String id, UploaderParams uploaderParams);


}
