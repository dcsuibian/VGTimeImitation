package com.dcsuibian.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Data
@Entity
public class Topic {
    @Id
    private Long id;

    private String content;

    private String title;

    private String cover;

    @Transient
    private User author;

    @Transient
    private User editor;

    /*
    注意，abstract为Java关键字，所以禁止使用。
    貌似JPA不是通过setter设置此值的，得这样让它写入。
     */
    @Column(name="abstract")
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private String __abstract;
    public void setAbstract(String value) {
        this.__abstract = value;
    }
    @Column(name="abstract")
    public String getAbstract() {
        return __abstract;
    }

    /*
    time为Unix时间戳，毫秒记，其实比较希望用Java8的Instant，但为了兼容其它库，内部还是用了Long
    */
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Long time;
    public void setTime(Instant time){
        this.time=time.toEpochMilli();
    }
    public void setTime(Date time){
        this.time=time.getTime();
    }
    public void setTime(Long time){
        this.time=time;
    }
    public Instant getTime(){
        return Instant.ofEpochMilli(this.time);
    }
    // 下面的JsonGetter和JsonSetter都是为Jackson提供的。转化成json时希望能用时间戳。为了不被随意访问特意设成私有的。
    @JsonGetter("time")
    private Long getTimeForJackson(){
        return this.time;
    }
    @JsonSetter("time")
    private void setTimeForJackson(Long time){
        this.time=time;
    }

    @Override
    public String toString()    {
        try {
            return "Topic:"+new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
