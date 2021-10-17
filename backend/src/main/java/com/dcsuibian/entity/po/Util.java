package com.dcsuibian.entity.po;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Util {
    static String listToString(List<String> list){
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
    static List<String> stringToList(String s){
        if(null==s){
            return null;
        }
        return new ArrayList<>(Arrays.asList(s.split("\\|")));
    }
}
