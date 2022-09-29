package com.ncvt.speed;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ncvt.speed.mapper")
public class SpeedApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpeedApplication.class, args);
    }

}
