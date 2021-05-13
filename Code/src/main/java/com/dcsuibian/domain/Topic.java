package com.dcsuibian.domain;

import java.io.Serializable;
import java.util.Date;

public class Topic implements Serializable {
    private Long id;
    private User author;
    private User editor;
    private String content;
    private String type;//新闻，攻略，评测
    private String title;
    private String resume;
    private String status;//草稿，提交，发布
    private Date modifyTime;
    private Date changeTime;
    private String coverImage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getEditor() {
        return editor;
    }

    public void setEditor(User editor) {
        this.editor = editor;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", author=" + author +
                ", editor=" + editor +
                ", content='" + content + '\'' +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", resume='" + resume + '\'' +
                ", status='" + status + '\'' +
                ", modifyTime=" + modifyTime +
                ", changeTime=" + changeTime +
                ", coverImage='" + coverImage + '\'' +
                '}';
    }
}
