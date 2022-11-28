package com.ncvt.speed.entity;

import lombok.Data;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class FileEntity {

    private String fileId;
    private String userId;
    private Integer duYou;
    private String fileName;
    private String fileType;
    private String filePath;
    private Long fileSize;
    private String hash;
    private String belongId;
    private String uploadTime;
    private String deleteId;
    private String folderBelongId;

    // 非数据库字段
    private Integer way;  // 上传方式 0分片上传, 1秒传
    private Integer shunk;
    private Integer shunks;
    private String belong;
    private String cBelong;
    private Long delete_time;  // 加入回收站时间
    private String surplusTime;  // 回收站保存剩余时间
    private String time;

    public static FileEntity getFE(String userId, File endFile, FileEntity fileEntity){
        fileEntity.setUserId(userId);
        fileEntity.setDuYou(0);
        if (fileEntity.getFilePath() == null) fileEntity.setFilePath((fileEntity.getBelong()+fileEntity.getFileName()).replace("\\","@-.@"));
        // 需要注意的是像【.】【|】【+】【*】等都是转义字符，在作为参数时，需要加入“\\”,
        String[] sName = fileEntity.getFileName().split("\\.");

        fileEntity.setFileType(sName[sName.length - 1].toLowerCase());
//                    fileEntity.setFilePath(endFile.getPath());
        if (endFile != null) fileEntity.setFileSize(endFile.length());
        Long timeStamp = System.currentTimeMillis();  //获取当前时间戳
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String sd = sdf.format(new Date(Long.parseLong(String.valueOf(timeStamp))));      // 时间戳转换成时间
        fileEntity.setUploadTime(sd);
        fileEntity.setDeleteId("0");
        return fileEntity;
    }

}
