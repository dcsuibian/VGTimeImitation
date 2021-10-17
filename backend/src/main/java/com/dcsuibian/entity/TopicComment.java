package com.dcsuibian.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class TopicComment {
    private Long id;
    private Instant time;
    private String content;
    @JsonBackReference
    private Topic topic;
}
