package com.dcsuibian.controller;

import com.dcsuibian.entity.Topic;
import com.dcsuibian.entity.qo.TopicQO;
import com.dcsuibian.entity.vo.PageWrapper;
import com.dcsuibian.entity.vo.ResponseWrapper;
import com.dcsuibian.service.TopicService;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.dcsuibian.util.Util.builder;

@Slf4j
@RestController
@RequestMapping("/api/topics")
public class TopicController {
    private TopicService service;

    @Autowired
    public TopicController(TopicService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseWrapper<PageWrapper<Topic>> get(
            @ApiParam(required = true) long pageNo,
            @ApiParam(required = true) long pageSize
    ) {
        TopicQO qo = new TopicQO();
        return builder(service.get(qo, pageNo, pageSize), "给你", 200);
    }

    @GetMapping("/{id}")
    public ResponseWrapper<Topic> getById(@PathVariable("id") long id) {
        Topic topic = service.getById(id);
        if (null != topic) {
            return builder(topic, "给你这个topic", 200);
        } else {
            return builder(null, "不存在这个topic", 404);
        }
    }

    @PostMapping
    public ResponseWrapper<Topic> add(@RequestBody Topic topic) {
        topic = service.add(topic);
        return builder(topic, "新增了一个topic", 201);
    }
}
