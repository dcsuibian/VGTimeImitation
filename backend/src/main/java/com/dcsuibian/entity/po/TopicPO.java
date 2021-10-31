package com.dcsuibian.entity.po;

import com.dcsuibian.entity.Topic;
import com.dcsuibian.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@Entity(name = "topic")
public class TopicPO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private String title;
    private String cover;
    private Long time;
    private Long authorId;
    private Long editorId;
    @Column(name = "abstract")
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private String __abstract;

    public static TopicPO convert(Topic topic) {
        if (null == topic) return null;
        TopicPO po = new TopicPO();
        po.setId(topic.getId());
        po.setContent(topic.getContent());
        po.setTitle(topic.getTitle());
        po.setCover(topic.getCover());
        po.setTime(null == topic.getTime() ? null : topic.getTime().toEpochMilli());
        po.setAuthorId(null == topic.getAuthor() ? null : topic.getAuthor().getId());
        po.setEditorId(null == topic.getEditor() ? null : topic.getEditor().getId());
        po.__abstract = topic.getAbstract();
        return po;
    }

    public static Topic convert(TopicPO po) {
        if (null == po) return null;
        Topic topic = new Topic();
        topic.setId(po.getId());
        topic.setContent(po.getContent());
        topic.setTitle(po.getTitle());
        topic.setCover(po.getCover());
        topic.setTime(null == po.getTime() ? null : Instant.ofEpochMilli(po.getTime()));
        if (null != po.getAuthorId()) {
            User author = new User();
            author.setId(po.getAuthorId());
            topic.setAuthor(author);
        }
        if (null != po.getEditorId()) {
            User editor = new User();
            editor.setId(po.getEditorId());
            topic.setEditor(editor);
        }
        topic.setAbstract(po.__abstract);
        return topic;
    }
}
