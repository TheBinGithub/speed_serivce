package com.ncvt.speed.entity;

import lombok.Data;

@Data
public class AccountEntity {

    private Long userId;
    private String userName;
    private String password;
    private String salt;

}
