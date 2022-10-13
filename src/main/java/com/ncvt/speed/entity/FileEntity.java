package com.ncvt.speed.entity;

import lombok.Builder;
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

}
