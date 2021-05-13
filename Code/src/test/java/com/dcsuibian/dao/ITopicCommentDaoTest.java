package com.dcsuibian.dao;

import com.dcsuibian.domain.TopicComment;
import com.dcsuibian.domain.TopicCommentGroup;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;

public class ITopicCommentDaoTest {
    private InputStream in;
    private SqlSession sqlSession;
    private ITopicDao topicDao;
    private IUserDao userDao;
    private ITopicCommentDao topicCommentDao;
    @Before
    public void before() throws Exception{
        in= Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(in);
        sqlSession=factory.openSession();
        topicDao=sqlSession.getMapper(ITopicDao.class);
        userDao=sqlSession.getMapper(IUserDao.class);
        topicCommentDao=sqlSession.getMapper(ITopicCommentDao.class);
    }
    @After
    public void after() throws Exception{
        sqlSession.commit();
        sqlSession.close();
        in.close();
    }
    @Test
    public void insertTest(){
        TopicComment tc=new TopicComment();
        tc.setTopic(topicDao.findById(5));
        tc.setSender(userDao.findById(6));
        tc.setReceiver(null);
        tc.setCommentGroupId(null);
        tc.setContent("nothing");
        tc.setTime(new Date());
        tc.setPraise(5L);
        System.out.println(tc);
        topicCommentDao.insert(tc);
        System.out.println(tc);
    }

    @Test
    public void queryByIdTest(){
        TopicComment tc=topicCommentDao.queryById(1);
        System.out.println(tc);
    }
    @Test
    public void queryByTopicIdTest(){
        var list=topicCommentDao.queryByTopicId(1);
        for(TopicComment tc:list){
            System.out.println(tc);
        }
        var t=TopicCommentGroup.getTopicCommentGroupList(list);
        System.out.println(t);
    }
}
