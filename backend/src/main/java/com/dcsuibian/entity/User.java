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
    private String gender;
    private String avatar;
    private Integer level;
    private String title; // 称号，或者叫头衔吧
    private String phoneNumber;
    private String email;
}
