package com.dcsuibian.entity.vo;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
public class ResponseWrapper<T> {
    private T data;
    private String message;
    private int code;
    private Long timestamp = Instant.now().toEpochMilli();

    public ResponseWrapper(T data, String message, int code) {
        this.data = data;
        this.message = message;
        this.code = code;
    }
}
