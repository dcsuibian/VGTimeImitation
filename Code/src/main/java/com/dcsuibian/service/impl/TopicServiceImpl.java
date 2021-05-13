package com.dcsuibian.service.impl;

import com.dcsuibian.dao.ITopicDao;
import com.dcsuibian.domain.Topic;
import com.dcsuibian.domain.User;
import com.dcsuibian.service.ITopicService;
import com.ibm.icu.text.BidiTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("topicService")
public class TopicServiceImpl implements ITopicService {
    @Autowired
    private ITopicDao topicDao;
    @Override
    public Topic findById(long id) {
        return topicDao.findById(id);
    }

    @Override
    public void addTopic(Topic topic) {
        topicDao.add(topic);
    }
    @Override
    public List<Topic> findByEditor(User user) {
        return topicDao.findByEditor(user);
    }

    @Override
    public Topic create(User editor) {
        Topic topic=new Topic();
        topic.setAuthor(editor);
        topic.setEditor(editor);
        topic.setContent("");
        topic.setType("新闻");
        topic.setTitle("");
        topic.setResume("");
        topic.setStatus("草稿");
        Date now=new Date();
        topic.setModifyTime(now);
        topic.setChangeTime(now);
        topic.setCoverImage(null);
        topicDao.add(topic);
        return topic;
    }

    @Override
    public void save(Topic topic) {
        topic.setStatus("草稿");
        topic.setModifyTime(new Date());
        topicDao.save(topic);
    }

    @Override
    public void submit(Topic topic) {
        topic.setStatus("提交");
        topic.setChangeTime(new Date());
        topicDao.update(topic);
    }

    @Override
    public List<Topic> findForAuditor() {
        List<Topic> ans=new ArrayList<>();
        List<Topic> all = topicDao.queryAll();
        for(Topic topic:all){
            if("提交".equals(topic.getStatus())){
                ans.add(topic);
            }
        }
        return ans;
    }
    @Override
    public List<Topic> findForAuditor(String type,String status,String queryStr,String orderBy,Long offset,Long limit) {
        if(null==limit&&null!=offset){
            limit=0x7fffffffffffffffL;
        }
        return topicDao.queryByConditions(null,null,type,status,queryStr,orderBy,offset,limit);
    }

    @Override
    public long findCountForAuditor(String type, String status, String queryStr) {
        return topicDao.queryCountByConditions(null,null,type,status,queryStr);
    }

    @Override
    public void passAudit(long id) {
        Topic topic=topicDao.findById(id);
        topic.setStatus("发布");
        topic.setChangeTime(new Date());
        topicDao.save(topic);
    }

    @Override
    public void failAudit(long id) {
        Topic topic=topicDao.findById(id);
        topic.setStatus("退回");
        topic.setChangeTime(new Date());
        topicDao.save(topic);
    }
}
