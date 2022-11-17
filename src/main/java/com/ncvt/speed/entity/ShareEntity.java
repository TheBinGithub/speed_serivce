package com.ncvt.speed.entity;

import lombok.Data;

@Data
public class ShareEntity {

    private String shareId;
    private String fileId;
    private String shareUrl;
    private String command;
    private String userId;
    private Long shareTime;
    private String shareType;
    private String userName;

}
