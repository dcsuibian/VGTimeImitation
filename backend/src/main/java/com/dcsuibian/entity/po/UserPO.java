package com.dcsuibian.entity.po;

import com.dcsuibian.entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "user")
public class UserPO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;
    private String gender;
    private String avatar;
    private Integer level;
    private String title;
    private String phoneNumber;
    private String email;
    public static User convert(UserPO po){
        User user=new User();
        user.setId(po.getId());
        user.setPassword(po.getPassword());
        user.setGender(po.getGender());
        user.setAvatar(po.getAvatar());
        user.setLevel(po.getLevel());
        user.setTitle(po.getTitle());
        user.setPhoneNumber(po.getPhoneNumber());
        user.setEmail(po.getEmail());
        return user;
    }
    public static UserPO convert(User user){
        UserPO po=new UserPO();
        po.setId(user.getId());
        po.setPassword(user.getPassword());
        po.setGender(user.getGender());
        po.setAvatar(user.getAvatar());
        po.setLevel(user.getLevel());
        po.setTitle(user.getTitle());
        po.setPhoneNumber(user.getPhoneNumber());
        po.setEmail(user.getEmail());
        return po;
    }
}