package com.dcsuibian.controller;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
class ResponseWrapper<T> {
    private T data;
    private String message;
    private int code;
    private Long timestamp=new Date().getTime();

    ResponseWrapper(T data, String message, int code) {
        this.data = data;
        this.message = message;
        this.code = code;
    }
}
public class Util {
    public static <T> ResponseWrapper<T> builder(T data, String message, int code){
        ResponseWrapper<T> res=new ResponseWrapper<>(data,message,code);
        return res;
    }
}
