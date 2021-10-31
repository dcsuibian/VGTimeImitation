package com.dcsuibian.controller;

import com.dcsuibian.entity.Topic;
import com.dcsuibian.entity.vo.HomePageVO;
import com.dcsuibian.entity.vo.ResponseWrapper;
import com.dcsuibian.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import static com.dcsuibian.util.Util.builder;


@RestController
@RequestMapping("/api/home-page")
public class HomePageController {
    private TopicService topicService;

    @Autowired
    public HomePageController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping
    public ResponseWrapper<HomePageVO> get() {
        HomePageVO vo = new HomePageVO();
        long[] hotNewsIds = {1139211, 1138949, 1138756, 1138950, 1138776};
        long[] guideIds = {1136155, 1136014, 1135950, 1135946};
        long[] reviewIds = {1138756, 1138950, 1138784, 1138766};
        long[] cultureIds = {1138949, 1136112, 1138423, 1137825};
        long[] cartoonIds = {1065667, 1065666, 1065665, 1065644};
        for (int i = 0; i < 5; i++) {
            long[] ids = null;
            switch (i){
                case 0:ids=hotNewsIds;break;
                case 1:ids=guideIds;break;
                case 2:ids=reviewIds;break;
                case 3:ids=cultureIds;break;
                case 4:ids=cartoonIds;break;
            }
            List<Topic> list = new ArrayList<>();
            Arrays.stream(ids).forEach(id->{
                list.add(topicService.getById(id));
            });
            switch (i){
                case 0:vo.setHotNews(list);break;
                case 1:vo.setGuides(list);break;
                case 2:vo.setReviews(list);break;
                case 3:vo.setCultures(list);break;
                case 4:vo.setCartoons(list);break;
            }
        }
        return builder(vo, "给你生成首页所需要的的所有信息", 200);
    }
}
