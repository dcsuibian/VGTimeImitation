package com.dcsuibian.service;

import com.alibaba.fastjson.JSONObject;
import com.dcsuibian.domain.Topic;
import com.openhtmltopdf.css.parser.property.PrimitivePropertyBuilders;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class ITopicServiceTest {
    private ITopicService ts;
    @Before
    public void before(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        ts=ac.getBean("topicService",ITopicService.class);
    }
    @Test
    public void findForAuditorTest(){
        List<Topic> list = ts.findForAuditor(null,null,null,null,5L,5L);
        for(Topic topic:list){
            System.out.println(topic.getId()+topic.getTitle());
        }
    }
    @Test
    public void submitTest(){
        Topic topic = ts.findById(1083297);
        System.out.println(topic);
        ts.submit(topic);
        System.out.println(topic);
    }
}
