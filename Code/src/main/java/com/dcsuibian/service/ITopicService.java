package com.dcsuibian.service;

import com.dcsuibian.domain.Topic;
import com.dcsuibian.domain.User;
import com.openhtmltopdf.css.parser.property.PrimitivePropertyBuilders;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ITopicService {
    Topic findById(long id);
    void addTopic(Topic topic);
//    public List<Topic> findByCondition(User editor,User author,String type,String status,String queryStr,String orderBy,Integer limit,Integer offset);
    List<Topic> findByEditor(User user);//为编辑找到所有文章
//    public List<Topic> findByCondition(Map<String,Object> condition);
    Topic create(User editor);
    void save(Topic topic);
    void submit(Topic topic);
    List<Topic> findForAuditor();
    List<Topic> findForAuditor(String type,String status,String queryStr,String orderBy,Long offset,Long limit);
    long findCountForAuditor(String type,String status,String queryStr);
    void passAudit(long id);
    void failAudit(long id);
}
