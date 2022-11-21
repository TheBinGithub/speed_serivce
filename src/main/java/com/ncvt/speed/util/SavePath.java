package com.ncvt.speed.util;

import lombok.Data;

import java.io.File;

@Data
public class SavePath {

    static String separator = File.separator;  // 获取文件名称分隔符, win \ ,linux/

    public static String savePath(){
        return separator+"bishe"+separator+"file"+separator;
    }

    public static String tempPath(){
        return separator+"bishe"+separator+"temppath"+separator;
    }

    // windows系统注释上面的属性和两个方法，使用下面的

//    static String separator = File.separator+File.separator;  // 获取文件名称分隔符, win \ ,linux/
//
//    public static String savePath(){
//        return separator+"D:"+separator+"bishe"+separator+"file"+separator;
//    }
//
//    public static String tempPath(){
//        return separator+"D:"+separator+"bishe"+separator+"temppath"+separator;
//    }


}
