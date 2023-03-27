package com.hyzcoding.jungle.common.pojo;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 *  评论实体
 * @author hyz
 * @date 2019/3/1
 * @version 1.0
 **/
@Entity
public class Comments implements Serializable {
    @Id
    @GeneratedValue
    private Integer commentsId;

    private String commentsContent;

    private Byte commentsType;

    private Integer parentId;

    private Timestamp commentsCreate;

    public Integer getCommentsId() {
        return commentsId;
    }

    public void setCommentsId(Integer commentsId) {
        this.commentsId = commentsId;
    }

    public String getCommentsContent() {
        return commentsContent;
    }

    public void setCommentsContent(String commentsContent) {
        this.commentsContent = commentsContent == null ? null : commentsContent.trim();
    }

    public Byte getCommentsType() {
        return commentsType;
    }

    public void setCommentsType(Byte commentsType) {
        this.commentsType = commentsType;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "commentsId=" + commentsId +
                ", commentsContent='" + commentsContent + '\'' +
                ", commentsType=" + commentsType +
                ", parentId=" + parentId +
                ", commentsCreate=" + commentsCreate +
                '}';
    }

    public Timestamp getCommentsCreate() {
        return commentsCreate;
    }

    public void setCommentsCreate(Timestamp commentsCreate) {
        this.commentsCreate = commentsCreate;
    }

}