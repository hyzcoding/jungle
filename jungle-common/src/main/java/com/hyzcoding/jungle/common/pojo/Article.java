package com.hyzcoding.jungle.common.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import java.io.Serializable;
import java.util.Date;

/**
 *  文章对象
 * @author hyz
 *  2019/3/2
 * @version 1.0
 **/
@Entity
@Document(indexName = "jungle", type = "article")
public class Article implements Serializable {
    /**
     * 文章elastic-search-id
     */
    @Id
    @GeneratedValue
    private String Id;
    /**
     * 文章mysql-id
     */
    private Integer articleId;
    /**
     * 文章描述
     */
    private String articleDescription;
    /**
     * 文章标题
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String articleTitle;
    /**
     * 文章内容
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String articleContent;
    /**
     * 文章观看数量
     */
    @Field(type = FieldType.Integer)
    private Integer articleViews;
    /**
     * 文章创建时间
     */
    @Field(type = FieldType.Integer)
    private Date articleCreate;
    /**
     * 文章板块
     */
    @Field(type = FieldType.Text)
    private String articleForum;
    /**
     * 文章点赞数
     */
    @Field(type = FieldType.Integer)
    private Integer articleLikes;
    /**
     * 提交用户id
     */
    @Field(type = FieldType.Integer)
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

    public String getArticleDescription() {
        return articleDescription;
    }

    public void setArticleDescription(String articleDescription) {
        this.articleDescription = articleDescription;
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

    public void setArticleForum(String articleForum) {
        this.articleForum = articleForum;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getArticleForum() {
        return articleForum;
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

    @Override
    public String toString() {
        return "Article{" +
                "Id='" + Id + '\'' +
                ", articleId=" + articleId +
                ", articleDescription='" + articleDescription + '\'' +
                ", articleTitle='" + articleTitle + '\'' +
                ", articleContent='" + articleContent + '\'' +
                ", articleViews=" + articleViews +
                ", articleCreate=" + articleCreate +
                ", articleForum='" + articleForum + '\'' +
                ", articleLikes=" + articleLikes +
                ", userId=" + userId +
                '}';
    }
}