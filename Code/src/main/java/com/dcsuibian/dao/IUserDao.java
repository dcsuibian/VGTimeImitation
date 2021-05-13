package com.dcsuibian.dao;

import com.dcsuibian.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IUserDao {
    //查询所有用户
    @Select("select * from user")
    @Results(id="userMap",value = {
            @Result(column = "phone_number",property = "phoneNumber"),
            @Result(column = "profile_picture",property = "profilePicture"),
            @Result(column = "real_name",property = "realName"),
            @Result(column = "identity_card",property = "identityCard")
    })
    public List<User> findAll();

    @Select("select * from user where id=#{id}")
    @ResultMap("userMap")
    public User findById(long id);

    @Select("select * from user where name=#{name}")
    @ResultMap("userMap")
    public User findByName(String name);

    @Select("select * from user where phone_number=#{phoneNumber}")
    @ResultMap("userMap")
    public User findByPhoneNumber(String phoneNumber);

    //保存用户信息
    @Insert("insert into user(name,password,role,phone_number,profile_picture,email,gender,real_name,identity_card) values(#{name},#{password},#{role},#{phoneNumber},#{profilePicture},#{email},#{gender},#{realName},#{identityCard})")
    public void addUser(User user);

    @Select("select * from user where id like CONCAT('%',#{queryStr},'%') or name like CONCAT('%',#{queryStr},'%') limit #{limit} offset #{offset}")
    List<User> findByCondition(@Param("queryStr") String queryStr,@Param("limit") int limit,@Param("offset") int offset);

    @Insert("insert into user(name,password,role,phone_number,profile_picture,email,gender,real_name,identity_card) values(#{name},#{password},#{role},#{phoneNumber},#{profilePicture},#{email},#{gender},#{realName},#{identityCard})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    void insert(User user);//增

    @Delete("DELETE FROM user WHERE id=#{id}")
    void delete(long id);//删

    @Update("UPDATE `user` SET name=#{name},password=#{password},role=#{role},phone_number=#{phoneNumber},profile_picture=#{profilePicture},email=#{email},gender=#{gender},real_name=#{realName},identity_card=#{identityCard} WHERE id=#{id}")
    void update(User user);//改
}
