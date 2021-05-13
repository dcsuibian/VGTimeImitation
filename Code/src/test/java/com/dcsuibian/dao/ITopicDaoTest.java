package com.dcsuibian.dao;

import com.dcsuibian.domain.Topic;
import com.dcsuibian.domain.User;
import com.openhtmltopdf.css.parser.property.PrimitivePropertyBuilders;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class ITopicDaoTest {
    private InputStream in;
    private SqlSession sqlSession;
    private ITopicDao topicDao;
    private IUserDao userDao;
    @Before
    public void before() throws Exception{
        in= Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(in);
        sqlSession=factory.openSession();
        topicDao=sqlSession.getMapper(ITopicDao.class);
        userDao=sqlSession.getMapper(IUserDao.class);
    }
    @After
    public void after() throws Exception{
        sqlSession.commit();
        sqlSession.close();
        in.close();
    }
    @Test
    public void saveTest(){
        Topic topic=topicDao.findById(1);
        topic.setContent("content");
        topic.setTitle("title");
        topic.setModifyTime(new Date());
        topicDao.save(topic);

    }
    @Test
    public void addTest(){
        User editor=new User();
        editor.setId(6L);
        Topic topic=new Topic();
        topic.setAuthor(editor);
        topic.setEditor(editor);
        topic.setContent("");
        topic.setType("新闻");
        topic.setTitle("");
        topic.setResume("");
        topic.setStatus("草稿");
        Date now=new Date();
        topic.setModifyTime(now);
        topic.setChangeTime(now);
        topic.setCoverImage(null);
        System.out.println(topic);
        topicDao.add(topic);
        System.out.println(topic);
    }
    @Test
    public void queryAllTest(){
        List<Topic> list = topicDao.queryAll();
        for(Topic topic:list){
            System.out.println(topic);
        }
    }
    @Test
    public void queryByConditionsTest(){
        User author=null;
//        User author=userDao.findByName("骑士");

        User editor=null;
//        User editor=userDao.findByName("游戏机实用技术");

        String type=null;
//        String type="新闻";

        String status=null;
//        String status="发布";

        String queryStr=null;
//        String queryStr="手柄";

        String orderBy=null;
//        String orderBy="id DESC";

//        Long offset=null;
        Long offset=3L;

        Long limit=null;
//        Long limit=5L;

        List<Topic> list = topicDao.queryByConditions(author,editor,type,status,queryStr,orderBy,offset,limit);
        for(Topic topic:list){
            System.out.println("id:"+topic.getId()+",author:"+topic.getAuthor().getName()+",editor:"+topic.getEditor().getName()+",title:"+topic.getTitle());
        }
    }
    @Test
    public void queryCountByConditionsTest(){
        System.out.println(topicDao.queryCountByConditions(null,null,null,null,null));
    }
    @Test
    public void someTest(){
        List<Topic> latestTopics = topicDao.queryByConditions(null, null, null, "发布", null, "change_time DESC", 0L, 5L);
        for(Topic topic:latestTopics){
            System.out.println(topic);
        }
    }
}
