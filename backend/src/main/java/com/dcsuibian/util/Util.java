package com.dcsuibian.util;

import com.dcsuibian.entity.vo.ResponseWrapper;

import java.util.ArrayList;
import java.util.Arrays;
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

    public static String listToString(List<String> list){
        if(null==list||0==list.size()){
            return null;
        }
        String result="";
        for(var item:list){
            if(item.length()>=1&&-1==item.indexOf("|")){
                result+="".equals(result)?item:"|"+item;
            }else{
                throw new RuntimeException("不支持的类型");
            }
        }
        return result;
    }

    public static List<String> stringToList(String s){
        if(null==s){
            return null;
        }
        return new ArrayList<>(Arrays.asList(s.split("\\|")));
    }
}
