package com.dcsuibian.controller;

import com.dcsuibian.entity.Topic;
import com.dcsuibian.repository.TopicRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("/api/topics")
public class TopicController {
    @Autowired
    TopicRepository topicRepository;
    @GetMapping
    @ResponseBody
    public String findAll() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Iterable<Topic> topics = topicRepository.findAll();
        String s = objectMapper.writeValueAsString(topics);
        return s;
    }
}
