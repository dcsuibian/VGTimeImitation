package com.dcsuibian.service;

import com.dcsuibian.entity.Topic;
import com.dcsuibian.entity.qo.TopicQO;
import com.dcsuibian.entity.vo.PageWrapper;

public interface TopicService{
    Topic add(Topic topic);
    Topic getById(long id);
    PageWrapper<Topic> get(TopicQO qo, long pageNo, long pageSize);
}
