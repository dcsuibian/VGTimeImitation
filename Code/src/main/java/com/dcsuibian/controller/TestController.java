package com.dcsuibian.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/test")
public class TestController {
//    @RequestMapping("/JSONTest")
//    @ResponseBody
//    public JSONObject JSONTest(@RequestBody String jsonStr, HttpSession session) {
//        JSONObject requestJSON = JSON.parseObject(jsonStr);
//        JSONObject responseJSON=new JSONObject();
//        System.out.println(requestJSON);
//        System.out.println(session);
//        responseJSON.put("status","success");
//        return responseJSON;
//    }
//    @RequestMapping("/SpringMVCTest")
//    public void SpringMVCTest(@RequestBody String jsonStr){
//        System.out.println(jsonStr);
//        return;
//    }
    @RequestMapping("/register")
    public void register(@RequestBody String jsonStr){
        System.out.println(jsonStr);
    }
}
