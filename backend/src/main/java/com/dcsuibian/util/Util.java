package com.dcsuibian.util;

import com.dcsuibian.entity.vo.ResponseWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Util {
    public static <T> ResponseWrapper<T> builder(T data, String message, int code){
        ResponseWrapper<T> res=new ResponseWrapper<>(data,message,code);
        return res;
    }
    public static <T, R> List<R> batchConvert(Iterable<T> iterable, Function<T, R> converter) {
        ArrayList<R> list = new ArrayList<>();
        for (var item : iterable) {
            list.add(converter.apply(item));
        }
        return list;
    }
}
