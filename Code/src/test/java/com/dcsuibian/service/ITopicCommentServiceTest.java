package com.dcsuibian.service;

import com.dcsuibian.domain.TopicComment;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ITopicCommentServiceTest {
    private ITopicCommentService tcs;
    private IUserService us;
    @Before
    public void before(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        tcs=ac.getBean("topicCommentService",ITopicCommentService.class);
        us=ac.getBean("userService",IUserService.class);
    }
    @Test
    public void replyToTopicTest(){
        TopicComment tc = tcs.replyToTopic(us.findById(6), 1, "test");
        System.out.println(tc);
    }
    @Test
    public void replyToTopicCommentTest(){
        TopicComment tc = tcs.replyToTopicComment(us.findById(6),4,"test2");
        System.out.println(tc);
    }
}
