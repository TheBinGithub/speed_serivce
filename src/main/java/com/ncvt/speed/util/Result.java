package com.ncvt.speed.util;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Result {

    private Integer code;
    private String msg;
    private Object data;

    public static Result ok(String msg) {
        return Result.builder().code(200).msg(msg).build();
    }

    public static Result ok(String msg, Object data) {
        return Result.builder().code(200).msg(msg).data(data).build();
    }

    public static Result fail(String msg) {
        return Result.builder().code(500).msg(msg).build();
    }

    public static Result fail(String msg,Object data) {
        return Result.builder().msg(msg).data(data).build();
    }

    public static Result fail(Integer code,String msg) {
        return Result.builder().code(code).msg(msg).build();
    }

}

