package com.dcsuibian.service.impl;

import com.dcsuibian.dao.ITopicCommentDao;
import com.dcsuibian.dao.ITopicDao;
import com.dcsuibian.domain.Topic;
import com.dcsuibian.domain.TopicComment;
import com.dcsuibian.domain.User;
import com.dcsuibian.service.ITopicCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("topicCommentService")
public class TopicCommentServiceImpl implements ITopicCommentService {
    @Autowired
    private ITopicCommentDao topicCommentDao;
    @Autowired
    private ITopicDao topicDao;

    @Override
    public TopicComment findById(long id) {
        return topicCommentDao.queryById(id);
    }

    @Override
    public List<TopicComment> findByTopicId(long topicId) {
        return topicCommentDao.queryByTopicId(topicId);
    }

    @Override
    public TopicComment replyToTopic(User sender, long topicId,String content) {
        Topic topic=topicDao.findById(topicId);
        TopicComment tc=new TopicComment();
        tc.setTopic(topic);
        tc.setSender(sender);
        tc.setReceiver(topic.getAuthor());
        tc.setContent(content);
        tc.setTime(new Date());
        tc.setPraise(0L);
        topicCommentDao.insert(tc);
        return tc;
    }

    @Override
    public TopicComment replyToTopicComment(User sender, long topicCommentId, String content) {
        var topicCommentToReply=topicCommentDao.queryById(topicCommentId);
        TopicComment ptc=(null==topicCommentToReply.getCommentGroupId()?topicCommentToReply:topicCommentDao.queryById(topicCommentToReply.getCommentGroupId()));
        TopicComment tc=new TopicComment();
        tc.setTopic(ptc.getTopic());
        tc.setSender(sender);
        tc.setReceiver(ptc.getSender());
        tc.setCommentGroupId(ptc.getId());
        tc.setContent(content);
        tc.setTime(new Date());
        tc.setPraise(0L);
        topicCommentDao.insert(tc);
        return tc;
    }
}
