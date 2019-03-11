package com.nightriver.jungle.common.pojo;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import java.io.Serializable;
import java.util.Date;
/**
 * @Description:    文章对象
 * @Author:         hyz
 * @CreateDate:     2019/3/2
 * @Version:        1.0
 **/
@Entity
public class Article implements Serializable {
    @Id
    @GeneratedValue
    private Integer articleId;

    private String articleTitle;

    private String articleContent;

    private Integer articleViews;

    private Date articleCreate;

    private Byte articleForum;

    private Integer articleLikes;

    private Integer userId;

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle == null ? null : articleTitle.trim();
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent == null ? null : articleContent.trim();
    }

    public Integer getArticleViews() {
        return articleViews;
    }

    public void setArticleViews(Integer articleViews) {
        this.articleViews = articleViews;
    }

    public Date getArticleCreate() {
        return articleCreate;
    }

    public void setArticleCreate(Date articleCreate) {
        this.articleCreate = articleCreate;
    }

    public Byte getArticleForum() {
        return articleForum;
    }

    public void setArticleForum(Byte articleForum) {
        this.articleForum = articleForum;
    }

    public Integer getArticleLikes() {
        return articleLikes;
    }

    public void setArticleLikes(Integer articleLikes) {
        this.articleLikes = articleLikes;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}