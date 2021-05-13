package com.itcast.test;

import com.dcsuibian.dao.ITopicDao;
import com.dcsuibian.dao.IUserDao;
import com.dcsuibian.domain.Topic;
import com.dcsuibian.domain.User;
import com.openhtmltopdf.css.parser.property.PrimitivePropertyBuilders;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class TestMyBatis {
    @Test
    public void run1() throws Exception{
        InputStream in= Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(in);
        SqlSession session=factory.openSession();
        IUserDao dao=session.getMapper(IUserDao.class);
        List<User> list=dao.findAll();
        for(User user:list){
            System.out.println(user);
        }
        session.close();
        in.close();
    }
    @Test
    public void run2() throws Exception{
        User user=new User();
        user.setName("云云众神");
        user.setPassword("password");
        user.setRole("普通用户");
        user.setPhoneNumber("110");
        user.setProfilePicture("https://img01.vgtime.com/headpic/2018/09/27/180927203455118.jpg?x-oss-process=image/resize,m_fill,h_40,w_40");
        user.setEmail("110@110.com");
        user.setGender("F");
        user.setRealName("真名");
        user.setIdentityCard("330482199007171537");
        InputStream in= Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(in);
        SqlSession session=factory.openSession();
        IUserDao dao=session.getMapper(IUserDao.class);
        dao.addUser(user);
        session.commit();
        session.close();
        in.close();
    }
    @Test
    public void run3() throws Exception{
        InputStream in= Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(in);
        SqlSession session=factory.openSession();
        ITopicDao dao = session.getMapper(ITopicDao.class);
        Topic topic = dao.findById(1);
        System.out.println(topic);
        session.close();
        in.close();
    }
    @Test
    public void run4() throws Exception{
        InputStream in= Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(in);
        SqlSession session=factory.openSession();
        IUserDao dao = session.getMapper(IUserDao.class);
        List<User> list = dao.findByCondition("", 10000, 1);
        for(User user:list){
            System.out.println(user);
        }
        session.close();
        in.close();
    }
    @Test
    public void run5() throws Exception{
        InputStream in= Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(in);
        SqlSession session=factory.openSession();
        ITopicDao topicDao=session.getMapper(ITopicDao.class);
        User user=new User();
        user.setId(6L);
        List<Topic> list=topicDao.findByEditor(user);
        for(Topic topic:list){
            System.out.println(topic);
        }
        session.close();
        in.close();
    }
}
