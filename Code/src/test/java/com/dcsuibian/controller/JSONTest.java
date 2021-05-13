package com.dcsuibian.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

public class JSONTest {
    @Test
    public void fun(){
        String jsonStr="{\"ID\": 1001, \"name\": \"张三\", \"age\": 24}";
        JSONObject object= JSON.parseObject(jsonStr);
        object.put("username",123);
        JSONArray array=new JSONArray();
        for(int i=0;i<10;i++){
            array.add(i);
        }
        object.put("array",array);
        System.out.println(object.toString());
        System.out.println(array);
        return;
    }
}
