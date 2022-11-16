package com.ncvt.speed.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class ZiFuLiu {

    /**
     * java.io.Reader 字符输入流
     * FileRead 文件字符输入流
     * int read(char[] c) 将字符读入字符数组
     */
    public static void FileReadDome(){
        try {
            FileReader fileReader = new FileReader("E:\\bishe\\a.txt");
            char[] c = new char[1024];
            int len = 0;
            while ((len  = fileReader.read(c)) != -1){
                System.out.println(new String(c,0,len));
            }
            fileReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * java.io.Write 字符输出流
     * FileWrite 文件字符输出流
     */

    // 步骤
    // 1. 创建对象
    // 2. 使用write方法写入到内存缓冲区（字符转字节）
    // 3. 使用flush，把缓冲区的数据刷新到文件中
    // 4. close
    public static void FileWriteDome(){
        // JDK7版本之后可以在try()里定义流对象,用完会自动close
        try(FileWriter writer = new FileWriter("E:\\bishe\\copy\\b.txt")) {
            /*
              Write(char[] b)
              Write(String str)
              Write(String str,int off, int len)
              append续写： FileWriter(File file, boolean append)
              换行符： windows: \r\n, linux: /n, mac:/r
             */
            writer.write(97);
//            writer.flush();  // 只刷新缓冲区，流对象可以继续使用
//            writer.close();  // 先flush一次再关闭流对象, 注意：需要处理close方法抛出的异常
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
