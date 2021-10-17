package com.dcsuibian.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
public class Topic {
    private Long id;
    private String content;
    private String title;
    private String cover;
    private User author;
    private User editor;
    private Instant time;
    @JsonManagedReference
    private List<TopicComment> comments;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private String __abstract;

    public void setAbstract(String value) {
        this.__abstract = value;
    }

    public String getAbstract() {
        return __abstract;
    }
}
