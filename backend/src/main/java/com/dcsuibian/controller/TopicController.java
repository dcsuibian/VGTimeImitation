package com.dcsuibian.controller;

import com.dcsuibian.entity.Topic;
import com.dcsuibian.repository.TopicRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.dcsuibian.controller.Util.builder;

@Slf4j
@RestController
@RequestMapping("/api/topics")
public class TopicController {
    private TopicRepository topicRepository;

    public TopicController(@Autowired TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @GetMapping
    public ResponseWrapper findAll() {
        Iterable<Topic> topics = topicRepository.findAll();
        return builder(topics, "给你所有topic", 200);
    }

    @GetMapping("/{id}")
    public ResponseWrapper findById(@PathVariable("id") Long id) {
        Optional<Topic> optionalTopic = topicRepository.findById(id);
        if (optionalTopic.isPresent()) {
            return builder(optionalTopic.get(), "给你这个topic", 200);
        } else {
            return builder(null, "不存在这个topic", 404);
        }
    }
    @PostMapping
    public ResponseWrapper save(@RequestBody Topic topic){
        topic = topicRepository.save(topic);
        return builder(topic,"新增了一个topic",201);
    }
}
