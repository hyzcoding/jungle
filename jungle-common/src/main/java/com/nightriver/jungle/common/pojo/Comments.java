package com.nightriver.jungle.common.pojo;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import java.io.Serializable;

/**
 * @Description:    评论实体
 * @Author:         hyz
 * @CreateDate:     2019/3/1
 * @Version:        1.0
 **/
@Entity
public class Comments implements Serializable {
    @Id
    @GeneratedValue
    private Integer commentsId;

    private String commentsContent;

    private Byte commentsType;

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
}