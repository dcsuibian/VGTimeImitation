package com.dcsuibian.domain;

import java.io.Serializable;
import java.util.Date;

public class TopicComment implements Serializable {
    private Long id;
    private Topic topic;
    private User sender;
    private User receiver;
    private Long commentGroupId;
    private String content;
    private Date time;
    private Long praise;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Long getCommentGroupId() {
        return commentGroupId;
    }

    public void setCommentGroupId(Long commentGroupId) {
        this.commentGroupId = commentGroupId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Long getPraise() {
        return praise;
    }

    public void setPraise(Long praise) {
        this.praise = praise;
    }

    @Override
    public String toString() {
        return "TopicComment{" +
                "id=" + id +
                ", senderId=" + sender.getId() +
                ", receiverId=" + (null==receiver?"null":receiver.getId()) +
                ", topicId=" + topic.getId() +
                ", commentGroupId=" + commentGroupId +
                ", content='" + content + '\'' +
                ", time=" + time +
                ", praise=" + praise +
                '}';
    }
}
