package com.dcsuibian.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private String title;
    private String cover;
    @ManyToOne(targetEntity = User.class)
    private User author;
    @ManyToOne(targetEntity = User.class)
    private User editor;
    /*
    注意，abstract为Java关键字，所以禁止使用。
    貌似JPA不是通过setter设置此值的，所以还得保留resume。
     */
    @Column(name="abstract")
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private String resume;//
    @Column(name="abstract")
    public void setAbstract(String value) {
        this.resume = value;
    }
    public String getAbstract() {
        return resume;
    }

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
    @JsonGetter("time")
    private Long getTimeForJackson(){
        return this.time;
    }
    @JsonSetter("time")
    private void setTimeForJackson(Long time){
        this.time=time;
    }
    @Override
    public String toString() {
        return "Topic(id=" + this.getId() + ", content=" + this.getContent() + ", title=" + this.getTitle() + ", abstract=" + this.resume + ", cover=" + this.getCover()+")";
    }

}
