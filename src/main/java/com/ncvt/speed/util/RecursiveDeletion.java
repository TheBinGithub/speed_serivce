package com.ncvt.speed.util;

import java.io.File;

/**
 * 递归删除文件类
 */
public class RecursiveDeletion {

    public static void deleteFile(File file){
        try {
            File[] files = file.listFiles();
            if (files!=null){//如果包含文件进行删除操作
                for (File f:files) {
                    //判断遍历出的文件是不是文件
                    if (f.isFile()){
                        //如果是则直接删除
                        f.delete();
                    }else {
                        deleteFile(f);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
