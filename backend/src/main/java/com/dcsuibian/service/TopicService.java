package com.dcsuibian.service;

import com.dcsuibian.entity.Topic;

public interface TopicService{
    Topic add(Topic topic);
    Topic getById(long id);
}
