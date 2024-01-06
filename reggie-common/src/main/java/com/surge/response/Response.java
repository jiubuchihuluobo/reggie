package com.surge.response;

import lombok.Data;

@Data
public class Response<T> {

    private int code;

    private String msg;

    private T data;

    public static <T> Response<T> success(T data) {
        Response<T> response = new Response<>();
        response.setCode(1);
        response.setData(data);
        return response;
    }

    public static <T> Response<T> failure(String msg) {
        Response<T> response = new Response<>();
        response.setCode(0);
        response.setMsg(msg);
        return response;
    }

}
