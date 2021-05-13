package com.dcsuibian.service;

import com.dcsuibian.domain.User;
import com.dcsuibian.util.UUIDProvider;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IUserServiceTest {
    private IUserService us;
    @Before
    public void before(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        us=ac.getBean("userService",IUserService.class);
    }
    @Test
    public void findByIdTest(){
        System.out.println(us.findById(1));
    }


}
