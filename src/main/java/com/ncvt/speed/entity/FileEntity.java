package com.ncvt.speed.entity;

import io.swagger.annotations.ApiImplicitParam;
import lombok.Data;

@Data
public class FileEntity {

    private Integer fileId;
    private String userId;
    private boolean duYou;
    private String fileName;
    private String fileType;
    private String filePath;
    private Long fileSize;
    private String hash;
    private String belong;
    private Long uploadTime;

    // 非数据库字段
    private Integer way;  // 上传方式 0分片上传, 1秒传
    private Integer shunk;
    private Integer shunks;

}
