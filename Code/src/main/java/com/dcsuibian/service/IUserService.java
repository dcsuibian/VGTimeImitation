package com.dcsuibian.service;

import com.dcsuibian.domain.User;
import com.mysql.cj.x.protobuf.MysqlxCrud;

import java.util.List;

public interface IUserService {
    //查询所有用户
    public List<User> findAll();
    public User findById(long id);
    public User findByName(String name);
    public User findByPhoneNumber(String phoneNumber);
    public User login(String phoneNumber,String password);
    //保存用户信息
    public void addUser(User user);
    public List<User> findByCondition(String queryStr,int limit,int offset);
    void update(User user);
}
