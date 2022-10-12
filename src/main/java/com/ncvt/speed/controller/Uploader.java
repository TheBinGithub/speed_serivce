package com.ncvt.speed.controller;

import io.swagger.annotations.Api;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Api(tags = "上下传模块")
public class Uploader {

    // file 文件
    // directory 文件夹/目录
    // path 路径

    //
    String path = File.pathSeparator;  // 获取系统路径分隔符, win ; ,linux :
    String separator = File.separator;  // 获取文件名称分隔符, win \ ,linux/

    private static void show01(String path){
        File f1 = new File(path);
        System.out.println(f1);
    }

    // paret父路径，child路径
    private static void show02(String paret, String child){
        File file = new File(paret,child);

        String s1 = file.getAbsolutePath(); // 返回File对象的绝对路径
        String s2 = file.getPath();  // 传递的路径
        String s3 = file.getName();  // 返回路径结尾部分
        Long l1 = file.length();  // 获取文件大小，以字节为单位；不能获取文件夹大小，如果文件不存在则返回0

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s1.toString());
        System.out.println(s3);
        System.out.println(l1);
    }

    // 父路径是File类型，可以使用File的方法对路径进行操作，再使用路径创建对象
    private static void show03(File paret, String child){
        File paret1 = new File("E:\\bishe");
        File file = new File(paret,child);

    }

    // File的判断方法
    public static void show4(){
        // exists() 文件或目录是否存在；
        // isDirectory 是否为目录，路径需要真实存在否则返回false
        // isFile 是否为文件，路径需要真实存在否则返回false
        // .endsWith(.txt) 判断字符串是否以.txt结尾
        // 转小写 .toLowerCase()
        File f1 = new File("E:\\bishe","a");
        boolean b1 = f1.getPath().endsWith("a");
        System.out.println("path:"+f1.getAbsolutePath());
        System.out.println(f1.exists());
        System.out.println("结尾："+b1);
    }

    // File的创建删除方法
    public static void show5(){
        // boolean createNewFile() 当且仅当文件不存在时，创建一个空文件
        // boolean delete() 删除由此File表示的文件或目录,删路径结尾部分,直接在硬盘删除不走回收站
        // boolean mkdir() 创建由此File表示的目录
        // boolean mkdirs() 创建由此File表示的目录，包括任何必须但不存在的父目录。

        File file1 = new File("E:\\bishe\\b.txt");
        File file2 = new File("E:\\bishe","c\\v\\f\\g");
        try {
            boolean result = file1.createNewFile();
            boolean result2 = file2.mkdirs();
            boolean result3 = file2.delete();
            System.out.println(result);
            System.out.println(result2);
            System.out.println(result3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // File的遍历方法
    public static void show6(){
        // String[] list() 返回String数组，表示该File目录中的所有子文件或目录
        // File[] listFiles() 返回一个File数组，表示该File目录中的所有子文件或目录
        // 如果目录不存在或不是目录都会跑出空指针异常
        File file1 = new File("E:\\bishe");
        String[] s = file1.list();
        File[] f = file1.listFiles();
        System.out.println(Arrays.toString(s));
        System.out.println(Arrays.toString(f));
    }

    // 文件过滤器接口
    // ListFiles(FileFilter filter)
    // ListFiles(FilenameFilter filter)

    private static final File file1 = new File("E:\\bishe");
    private static final List<File> lists = new ArrayList<>();
    public static void show7(File file1){
        // 如果accept()返回true则把传进来的参数放到File数组里
        File[] files1 = file1.listFiles( (e) -> {
            if (e.isDirectory()){
                return true;
            }
            return e.getPath().toLowerCase().endsWith(".txt");
        });

        assert files1 != null;
        for (File file : files1){
            if (file.isDirectory()){
                show7(file);
            }else {
                lists.add(file);
            }
        }
    }

    // FileOutputStream  // 文件字节输出流，内存写到硬盘（原理：Java程序>JVM>OS>OS调写数据的方法写入）
    // FileOutputStream(String name, boolean append); 目的地是文件的路径,append追加写的开关，在文件的末尾写
    // FileOutputStream(File file, boolean append); 目的地是一个文件
    // 创建一个FileOutputStream对象，会根据参数创建一个空文件，把对象指向创建好的文件

    // 字节输出流使用步骤：
    // 1、创建一个FileOutputStream对象，传入参数,一个参数是覆盖，两个是续写
    // 2、调用对象的write方法，写入到文件
    // 3、释放资源
    public static void FileOutputStreamDome(){
        try {
            FileOutputStream fos = new FileOutputStream("E:\\bishe\\a.txt",true);
            // write(byte[] b), write(byte[] b, int off, int len),off是从哪开始写，len是写多少个
            // 如果写的第一个字节是正数（0-127），查询ascii
            // 如果第一个是是负，会和第二个字节组成一个中文显示，查询系统编码表
            // byte[] getBytes() 把字符串转成字节数组
//            byte[] bytes = {-65,-66,49,48};
            // 换行符： windows: \r\n, linux: /n, mac:/r
            byte[] bytes = "牛皮".getBytes();
            fos.write(bytes);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 文件字节输入流，从硬盘读取到内存
    // FileInputStream(String name) name: 路径
    // FileInputStream(File file) file: 文件
    // 1、创建一个FileInputStream对象
    // 2、会把对象指定构造方法中要读取的文件

    // 读取数据的原理： Java程序>JVM>OS>OS读取数据的方法
    // 步骤：
    // 1、创建一个FileInputstream对象，构造方法中绑定要读取的数据源
    // 2、使用read方法读取
    // 3、释放资源
    public static void FileInputStreamDome(){
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("E:\\bishe\\a.txt");
            // int read() 读取文件一个字节.读一次指针下移一位，末尾就返回-1
            // int read(byte[] b) 读取多个字节，b的作用为
            // String(byte[] b),能把字节转为字符串
            byte[] b = new byte[2];  // b,读取的字节长度，一般为1024整数倍（1kb）
            int result = fis.read(b);  // result为读取到的有效字节个数，返回-1则读取完毕
            System.out.println(Arrays.toString(b));
            System.out.println(result);
            System.out.println(new String(b));
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 文件复制，边读边写
    public static boolean copyFile(String file,String path){
        try {
            long s = System.currentTimeMillis();
            FileInputStream rede = new FileInputStream(file);
            FileOutputStream write = new FileOutputStream(path);
            byte[] b = new byte[1024];
            int len = 0;
            while ((len = rede.read(b)) != -1){
                write.write(len);
            }
            write.close();
            rede.close();

            long e = System.currentTimeMillis();
            System.out.println("耗时："+(e-s)+"ms");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

//    public static void main(String[] args) {
////        show02("E:\\bishe","a.txt");
////        show7(file1);
////        System.out.println(lists);
//
//        boolean b = copyFile("E:\\bishe\\a.txt","E:\\bishe\\copy\\a.txt");
//        System.out.println(b);
//    }

}

