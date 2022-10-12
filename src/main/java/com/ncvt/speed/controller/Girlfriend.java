package com.ncvt.speed.controller;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

@Builder
@Slf4j
public class Girlfriend {

    private String name;

    @Override
    public String toString() {
        return "对象集{" +
                "name='" + name + '\'' +
                '}';
    }

//    public static Girlfriend newGirlfriend(String name){
//        Girlfriend girlfriend = new Girlfriend();
//        girlfriend.name = name;
//        return girlfriend;
//    }

    public static Girlfriend newGirlfriend(String name){
        return Girlfriend.builder().name(name).build();
    }

//    public static void main(String[] args) {
//        Girlfriend girlfriend = newGirlfriend("小红");
//        log.info(girlfriend.toString());
//    }
}
