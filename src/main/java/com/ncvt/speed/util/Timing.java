package com.ncvt.speed.util;

import com.ncvt.speed.entity.FileEntity;
import com.ncvt.speed.mapper.DeleteMapper;
import com.ncvt.speed.mapper.FileMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 定时器
 */
@Component
@Slf4j
public class Timing {

    @Resource
    private FileMapper fileMapper;

    @Resource
    private DeleteMapper deleteMapper;

    private static final String separator = File.separator;  // 获取文件名称分隔符, win \ linux /

    private final String savePath = SavePath.savePath();

    @Scheduled(cron = "0 0 3 * * ?")
//    @Scheduled(cron = "0/5 * * * * *")
    public void timingDelete(){
        log.info("定时删除 ...");
        Long timeStamp = System.currentTimeMillis();  //获取当前时间戳
        // 获取过期的数据
        List<FileEntity> deleteList = fileMapper.queryAllDelete(timeStamp);
        // 获取彻底删除的数据
        List<FileEntity> cdfList = fileMapper.queryFileByD();
        if (!deleteList.addAll(cdfList)) log.info("合并出现未知异常！");
        // 删除文件的地址列表
        List<String> deletePathList = new ArrayList<>();
        // 删除tb_file的id列表
        List<String> fList = new ArrayList<>();
        // 删除tb_delete的id列表
        List<String> dList = new ArrayList<>();
        for (FileEntity d : deleteList){
            // 添加需要删除的文件地址
            List<FileEntity> hList = fileMapper.queryHash(d.getHash());
            if (hList.size() == 1){
                for (FileEntity h : hList){
                    deletePathList.add(h.getFilePath().replace("@-.@", separator));
                }
            }
            // 添加删除的tb_file
            fList.add(d.getFileId());
            // 添加删除的tb_delete
            if (!d.getDeleteId().equals("1")) dList.add(d.getDeleteId());
        }
        if (deletePathList.size() != 0){
            // 此处删除实际文件
            log.info("删除实际文件 ...");
            try {
                for (String s : deletePathList){
                    File file = new File(savePath+s);
                    boolean b = file.delete();
                    if (!b) log.info("删除 " + s + "出现未知异常！");
                }
            }catch (Exception e){
                e.printStackTrace();
                log.info("批量删除实际文件出现未知异常！");
            }
        }
        // 此处删除数据库记录
        int fResult = fileMapper.deleteFileById(fList);
        int dResult = deleteMapper.deleteById(dList);
        if (deleteList.size() != fResult) log.info("删除F出现未知异常！");
        if (deleteList.size() != dResult) log.info("删除D出现未知异常！");
        log.info("定时删除完成！！！");
//        Long t = timeStamp - deleteList.get(0).getDelete_time();
//        System.out.println("剩余时间(ms): " + (604800000 - t));
//        System.out.println("d:" + deleteList);
//         七天的毫秒数：604800000
    }

}
