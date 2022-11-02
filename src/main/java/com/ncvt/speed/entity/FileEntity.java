package com.ncvt.speed.entity;

import io.swagger.annotations.ApiImplicitParam;
import lombok.Data;

@Data
public class FileEntity {

    private Integer fileId;
    private String userId;
    private Integer duYou;
    private String fileName;
    private String fileType;
    private String filePath;
    private Long fileSize;
    private String hash;
    private Integer belongId;
    private String uploadTime;
    private Integer deleteId;

    // 非数据库字段
    private Integer way;  // 上传方式 0分片上传, 1秒传
    private Integer shunk;
    private Integer shunks;
    private String belong;
    private String cBelong;
    private Long deleteTime;  // 加入回收站时间
    private String surplusTime;  // 回收站保存剩余时间
    private String time;

}
