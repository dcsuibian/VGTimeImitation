package com.itcast.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcsuibian.domain.User;

public class TestFastjson {
    public static void main(String[] args) {
//        String jsonStr="{\"phoneNumber\":\"667\",\"password\":\"767767\",\"realName\":null,\"identityCard\":null}";
//        JSONObject object = JSONObject.parseObject(jsonStr);
//        String s=object.getString("identityCard");
//        System.out.println(object);
//        System.out.println(null==s);

        String jsonStr="{\"phoneNumber\":\"667\",\"password\":\"767767\",\"realName\":\"ffff\",\"identityCard\":null}";
        JSONObject requestData = JSONObject.parseObject(jsonStr);
        User user=new User();
        user.setRealName("testUser");
        user.setPhoneNumber(requestData.getString("phoneNumber"));
        user.setPassword(requestData.getString("password"));
        user.setRealName(requestData.getString("realName"));
        user.setIdentityCard(requestData.getString("identityCard"));
        System.out.println(jsonStr);
        System.out.println(requestData);
        System.out.println(null==requestData.getString("identityCard"));
        System.out.println(user);
    }
}
