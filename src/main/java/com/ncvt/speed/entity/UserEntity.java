package com.ncvt.speed.entity;

import lombok.Data;

@Data
public class UserEntity {

    private String userId;
    private String nickname;
    private String allSpace;
    private String usedSpace;
    private String secondPassword;
    private String sSalt;

}
