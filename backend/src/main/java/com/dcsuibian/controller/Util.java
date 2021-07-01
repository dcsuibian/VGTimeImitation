package com.dcsuibian.controller;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
class ResponseWrapper {
    private Object data;
    private String message;
    private int code;
    private Long timestamp=new Date().getTime();

    ResponseWrapper(Object data, String message, int code) {
        this.data = data;
        this.message = message;
        this.code = code;
    }
}
public class Util {
    public static ResponseWrapper builder(Object data, String message, int code){
        ResponseWrapper res=new ResponseWrapper(data,message,code);
        return res;
    }
}
