package com.dcsuibian.controller;

import com.dcsuibian.repository.TopicRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static com.dcsuibian.controller.Util.builder;

@RestController
@RequestMapping("/api/homepage")
public class HomePageController {
    private TopicRepository topicRepository;

    public HomePageController(@Autowired TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @GetMapping
    public ResponseWrapper getAllHomePageData(){
        Map<String,Object> map=new HashMap<>();
        Object[] focus={1127684,1127473,1127561,1127002,1125185};
        Object[] news={};
        Object[] cheat={1124746,1124745,1124744,1124680};
        Object[] review={1127473,1127165,1127002,1126671};
        Object[] culture={1127737,1127684,1127651,1127571};
        Object[] comic={1065667,1065666,1065665,1065644};
        map.put("focus",focus);
        map.put("news",news);
        map.put("cheat",cheat);
        map.put("review",review);
        map.put("culture",culture);
        map.put("comic",comic);
        for(String key:map.keySet()){
            Object[] arr=(Object[])map.get(key);
            for(int i=0;i<arr.length;i++){
                arr[i]=topicRepository.findById((long)(int)arr[i]);
            }
        }
        return builder(map,"给你生成首页所需要的的所有信息",200);
    }
}
