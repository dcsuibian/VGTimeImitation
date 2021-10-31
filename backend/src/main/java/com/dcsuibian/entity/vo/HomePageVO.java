package com.dcsuibian.entity.vo;

import com.dcsuibian.entity.Topic;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HomePageVO {
    private List<Topic> hotNews;// 焦点新闻
    private List<Topic> news;// 新闻资讯
    private List<Topic> guides; // 攻略资料
    private List<Topic> reviews; // 深度评测
    private List<Topic> cultures;// 游戏文化
    private List<Topic> cartoons; // 动漫时光
}