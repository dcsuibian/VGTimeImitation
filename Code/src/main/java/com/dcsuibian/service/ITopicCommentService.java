package com.dcsuibian.service;

import com.dcsuibian.domain.Topic;
import com.dcsuibian.domain.TopicComment;
import com.dcsuibian.domain.User;

import java.util.List;

public interface ITopicCommentService {
    TopicComment findById(long id);
    List<TopicComment> findByTopicId(long topicId);
    TopicComment replyToTopic(User sender, long topicId,String content);
    TopicComment replyToTopicComment(User sender, long topicCommentId,String content);
}
