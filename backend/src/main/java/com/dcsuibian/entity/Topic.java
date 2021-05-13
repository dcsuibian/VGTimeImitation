package com.dcsuibian.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private User author;
    @ManyToOne
    private User editor;
    private String content;
    private String type;//新闻，攻略，评测
    private String title;
    private String resume;
    private String status;//草稿，提交，发布
    private Date modifyTime;
    private Date changeTime;
    private String coverImage;
}
