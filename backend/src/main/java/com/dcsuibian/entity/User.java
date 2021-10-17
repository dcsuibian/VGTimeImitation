package com.dcsuibian.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private Long id;
    private String name;
    private String password;
    private String gender;
    private String avatar;
    private Integer level;
    private String title; // 称号，或者叫头衔吧
    private String phoneNumber;
    private String email;
}
