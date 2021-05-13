package com.dcsuibian.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String password;
    private String role;
    private String phoneNumber;
    private String profilePicture;
    private String email;
    private String gender;
    private String realName;
    private String identityCard;
}
