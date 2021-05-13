package com.itcast.test;

import com.dcsuibian.service.IUserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
    @Test
    public void run1(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        IUserService us=(IUserService)ac.getBean("userService");
        us.findAll();
    }
}
