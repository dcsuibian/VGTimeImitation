package com.dcsuibian.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String content;
    private String title;
    @Column(name="abstract")
    @Getter(AccessLevel.NONE)
    private String resume;// 注意，abstract为Java关键字，所以禁止使用
//    @Temporal(TemporalType.DATE)
//    private Date time;

    private void setResume(String resume){
        this.resume=resume;
    }
    @Column(name="abstract")
    public String getAbstract() {
        return resume;
    }

    public void setAbstract(String value) {
        this.resume = value;
    }

    @Override
    public String toString() {
        return "Topic(id=" + this.getId() + ", content=" + this.getContent() + ", title=" + this.getTitle() + ", abstract=" + this.resume + ")";
    }

}
