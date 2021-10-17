package com.dcsuibian.service;

import com.dcsuibian.entity.Topic;

public interface TopicService extends CommonService<Topic>{
    Topic add(Topic topic);
}
