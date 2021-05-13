package com.dcsuibian.service;

import com.dcsuibian.dao.ITopicDao;
import com.dcsuibian.dao.IUserDao;
import com.dcsuibian.domain.Topic;
import com.dcsuibian.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class TestITopicService {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private ITopicDao topicDao;
    private IUserDao userDao;
    @Before
    public void before() throws IOException {
        in= Resources.getResourceAsStream("mybatis-config.xml");
        factory=new SqlSessionFactoryBuilder().build(in);
        session=factory.openSession();
        topicDao=session.getMapper(ITopicDao.class);
        userDao=session.getMapper(IUserDao.class);
    }
    @After
    public void after() throws IOException {
        session.commit();
        session.close();
        in.close();
    }
    @Test
    public void testSaveTopic() throws Exception{
        File file=new File("C:\\study\\Visual Studio Code\\test\\TestShowdown\\test.md");
        FileInputStream inputStream=new FileInputStream(file);
        byte[] bytes=new  byte[inputStream.available()];
        inputStream.read(bytes);
        inputStream.close();
        User user=userDao.findById(6);
        Topic topic=new Topic();
        topic.setEditor(user);
        topic.setAuthor(user);
        topic.setContent(new String(bytes));
        topic.setType("评测");
        topic.setTitle("《精灵与萤火意志》评测：拿起手柄就停不下来");
        topic.setResume("爱，是超越一切的情感。");
        topic.setStatus("发布");
        topic.setModifyTime(new Date());
        topic.setChangeTime(new Date());
        topic.setCoverImage("https://img01.vgtime.com/game/cover/2020/03/09/200309164007449_u59.jpg?x-oss-process=image/resize,m_pad,color_000000,w_800,h_500");
        topicDao.add(topic);
    }
}
