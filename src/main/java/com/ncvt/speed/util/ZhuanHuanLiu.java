package com.ncvt.speed.util;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 转换流,解决编码的问题,可以指定编码表
 * InputStreamReader
 * OutputStreamWrite
 */
public class ZhuanHuanLiu {

    // OutputStreamWrite(OutputStream out, String charsetName) charsetName不区分大小写,默认ide的编码表
    public static void show01(){

        try {
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("E:\\bishe\\copy\\g.txt"), StandardCharsets.UTF_8);
            osw.write(97);
            osw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
