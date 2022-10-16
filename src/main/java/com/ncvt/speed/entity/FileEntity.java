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

    // 非数据库字段
    private Integer shunk;
    private Integer shunks;

}
