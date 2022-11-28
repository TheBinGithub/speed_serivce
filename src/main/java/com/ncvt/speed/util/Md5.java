package com.ncvt.speed.util;

import org.springframework.util.DigestUtils;

/**
 * md5加密类
 */
public class Md5 {

    // md5算法加密处理
    public static String getMd5Password(String password,String salt){
        // md5加密算法,三次
        for (int i=0;i<3;i++){
            password = DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();
        }
        return password;
    }

}
