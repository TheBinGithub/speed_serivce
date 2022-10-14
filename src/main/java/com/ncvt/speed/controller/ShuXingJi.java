package com.ncvt.speed.controller;

import java.io.*;
import java.util.Arrays;
import java.util.Properties;
import java.util.Set;

/**
 * 属性集
 * java.util.Properties集合 extends Hashtable<k,v> implements Map<k,v>
 * Properties类表示一个持久的属性集，可保存在流中或从流中加载
 * Properties集合是唯一一个额IO流相结合的集合
 * store方法写入到硬盘
 * load方法读取到集合中使用
 * Properties集合是一个双列集合，k v默认都是字符串
 */
public class ShuXingJi {

    private static final String name = "E:\\bishe\\copy";

    private static void show01() {
        // Object setProperty(String k,String v) 相当于Hashtable的put
        // Object getProperty(String k) 通过k找v 相当于Map的get(k)
        Properties prop = new Properties();
        prop.setProperty("1","2");
        // stringPropertyNames,取出所有的键
        Set<String> set = prop.stringPropertyNames();
        for (String k :set){
            String v = prop.getProperty(k);
            System.out.println(k+":"+v);
        }
    }

    /*
      Properties集合的store
      void store(OutputStream out, String comments) out字节输出流，不能中文。comments注释，不能中文
      void store(Writer writer, String comments) writer字符输出流，可以中文
      步骤:
      1、创建Properties对象，添加数据
      2、创建流对象
      3、使用store方法，把数据持久化到硬盘
      4、close
     */
    public static void show02(){
        Properties prop = new Properties();
        prop.setProperty("1","啊？");
        prop.setProperty("2","5");

       try {
           FileWriter fw = new FileWriter("E:\\bishe\\copy\\b.txt");
           prop.store(fw,"save data");
           fw.close();
       } catch (IOException e) {
           e.printStackTrace();
       }
    }

    /*
      Properties集合的Loda
      void Load(InputStream inStream) 字节输入流，不能读有中文的键值对
      void Load(Reader reader) 字符输入流，能读取含有中文的键值对
      1、存储键值对的文件中，可以用#注释，被注释的键值对不会再被读取。读取来默认是字符串
     */
    public static void show03(){
        Properties prop = new Properties();
        try {
            prop.load(new FileReader("E:\\bishe\\copy\\b.txt"));
            Set<String> set = prop.stringPropertyNames();
            for (String k:set){
                String v = prop.getProperty(k);
                System.out.println(k+":"+v);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 缓冲流,
    // BufferedInputStream(InputStream in, int size)
    // BufferedOutputStream(OutputStream out, int size)
    // BufferedReaderStream(ReadStream read, int size)
    // BufferedWriteStream(WriteStream write, int size)
    public static void show04(){
        try {
            FileOutputStream fops = new FileOutputStream(name+"\\b.txt");
            BufferedOutputStream bos = new BufferedOutputStream(fops);
            bos.write("写入缓冲区".getBytes());
//            bos.flush();
            bos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void show05(){
        try {
            FileInputStream fis = new FileInputStream(name+"\\b.txt");
            BufferedInputStream bis = new BufferedInputStream(fis);
            byte[] b = new byte[1024];
            int len = 0;
            while ((len = bis.read(b)) != -1){
                System.out.println(new String(b,0,len));
            }
            bis.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // BufferedWriter特有的方法， void newLin() 写入一个换行符
    public static void show06(){
        try {
            FileWriter fw = new FileWriter(name+"\\c.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("字符缓冲输出流");
//            bw.newLine();
            bw.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // BufferedReader特有的方法 String readLine() 读取一个文本行,读取一行数据
    public static void show07(){
        try {
            FileReader fr = new FileReader(name+"\\d.txt");
            BufferedReader br = new BufferedReader(fr);
            char[] c = new char[1024];
            int len = 0;
            while ((len = br.read(c)) != -1){
                System.out.println(new String(c,0,len));
            }
            String s = br.readLine();
            System.out.println("s:"+s);

        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
