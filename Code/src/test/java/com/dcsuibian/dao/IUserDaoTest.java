package com.dcsuibian.dao;

import com.dcsuibian.domain.User;
import com.dcsuibian.util.UUIDProvider;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

public class IUserDaoTest {
    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;
    @Before
    public void before() throws Exception{
        in= Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(in);
        sqlSession=factory.openSession();
        userDao=sqlSession.getMapper(IUserDao.class);
    }
    @After
    public void after() throws Exception{
        sqlSession.commit();
        sqlSession.close();
        in.close();
    }

    @Test
    public void doSomething() {
        for(int i=0;i<10;i++){
            User user=new User();
            user.setName(UUIDProvider.getShortUUID(14));
            user.setPassword("password");
            user.setRole("普通用户");
            user.setPhoneNumber(Integer.toString(683+i));
            user.setGender("N");
            userDao.insert(user);
        }
    }
}
