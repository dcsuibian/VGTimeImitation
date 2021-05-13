package com.dcsuibian.service.impl;

import com.dcsuibian.dao.IUserDao;
import com.dcsuibian.domain.User;
import com.dcsuibian.service.IUserService;
import com.dcsuibian.util.UUIDProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("userService")
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;
    @Override
    public List<User> findAll() {
        System.out.println("业务层：查询所有用户");
        return userDao.findAll();
    }

    @Override
    public User findById(long id) {
        return userDao.findById(id);
    }

    @Override
    public User findByName(String name) {
        return userDao.findByName(name);
    }

    @Override
    public User findByPhoneNumber(String phoneNumber) {
        return userDao.findByPhoneNumber(phoneNumber);
    }

    @Override
    public User login(String phoneNumber, String password) {
        User user=findByPhoneNumber(phoneNumber);
        if(null==user){
            return null;
        }
        if(null==password||(!password.equals(user.getPassword()))){
            return null;
        }
        return user;
    }

    @Override
    public void addUser(User user) {
        System.out.println("业务层：保存用户。。。");
        user.setName("手机用户"+ UUIDProvider.getShortUUID(12));
        user.setRole("普通用户");
        user.setProfilePicture("/image/tou.gif");
        user.setGender("N");
        userDao.addUser(user);
    }

    @Override
    public List<User> findByCondition(String queryStr, int limit, int offset) {
        return userDao.findByCondition(queryStr,limit,offset);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }
}
