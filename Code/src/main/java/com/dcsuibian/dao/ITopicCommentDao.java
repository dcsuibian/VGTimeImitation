package com.dcsuibian.dao;

import com.dcsuibian.domain.TopicComment;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITopicCommentDao {
    @Insert("INSERT INTO topic_comment(topic_id,sender_id,receiver_id,comment_group_id,content,time,praise) VALUES(#{topic.id},#{sender.id},#{receiver.id},#{commentGroupId},#{content},#{time},#{praise})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    void insert(TopicComment topicComment);

    @Select("SELECT * FROM topic_comment WHERE id=#{id}")
    @Results(id="topicCommentMap",value={
            @Result(column = "comment_group_id",property = "commentGroupId"),
            @Result(column = "topic_id",property = "topic",one = @One(select = "com.dcsuibian.dao.ITopicDao.findById",fetchType = FetchType.EAGER)),
            @Result(column = "sender_id",property = "sender",one = @One(select = "com.dcsuibian.dao.IUserDao.findById",fetchType = FetchType.EAGER)),
            @Result(column = "receiver_id",property = "receiver",one = @One(select = "com.dcsuibian.dao.IUserDao.findById",fetchType = FetchType.EAGER)),
    })
    TopicComment queryById(long id);

    @Select("SELECT * FROM topic_comment WHERE topic_id=#{topicId} ORDER BY time ASC")
    @ResultMap("topicCommentMap")
    List<TopicComment> queryByTopicId(long topicId);
}
