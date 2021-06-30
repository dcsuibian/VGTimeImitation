package com.dcsuibian.controller;

import com.dcsuibian.entity.Topic;
import com.dcsuibian.repository.TopicRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/topics")
public class TopicController {
    @Autowired
    TopicRepository topicRepository;
    @GetMapping
    public Iterable<Topic> findAll() {
//        PageRequest pageRequest= PageRequest.of(0,12, Sort.by("id") );
//        Iterable<Topic> topics = topicRepository.findAll(pageRequest).getContent();
        Iterable<Topic> topics = topicRepository.findAll();
        return topics;
    }
    @GetMapping("/{id}")
    public Topic findById(@PathVariable("id") Long id){
        Optional<Topic> optionalTopic = topicRepository.findById(id);
        return optionalTopic.isPresent()?optionalTopic.get():null;
    }
}
