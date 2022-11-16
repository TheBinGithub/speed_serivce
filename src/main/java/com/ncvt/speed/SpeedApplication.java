package com.ncvt.speed;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

// scanBasePackages = {"com.ncvt.speed.config"}
@SpringBootApplication
// 开启定时器
@EnableScheduling
// 扫描dao
@MapperScan("com.ncvt.speed.mapper")
public class SpeedApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpeedApplication.class, args);
    }

}
