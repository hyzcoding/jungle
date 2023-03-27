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
 *  用户信息实体
 * @author hyz
 * @date 2019/3/2
 * @version 1.0
 **/
@Entity
@Document(indexName = "jungle", type = "user")
public class UserInfo implements Serializable {
    @Id
    @GeneratedValue
    private Integer userId;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String userName;

    private Byte userSex;

    private Integer userFollowing;

    private Integer userFollowers;

    private Integer userClass;

    private Integer userScore;

    private String userAvatar;

    private Date userSign;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Byte getUserSex() {
        return userSex;
    }

    public void setUserSex(Byte userSex) {
        this.userSex = userSex;
    }

    public Integer getUserFollowing() {
        return userFollowing;
    }

    public void setUserFollowing(Integer userFollowing) {
        this.userFollowing = userFollowing;
    }

    public Integer getUserFollowers() {
        return userFollowers;
    }

    public void setUserFollowers(Integer userFollowers) {
        this.userFollowers = userFollowers;
    }

    public Integer getUserClass() {
        return userClass;
    }

    public void setUserClass(Integer userClass) {
        this.userClass = userClass;
    }

    public Integer getUserScore() {
        return userScore;
    }

    public void setUserScore(Integer userScore) {
        this.userScore = userScore;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar == null ? null : userAvatar.trim();
    }

    public Date getUserSign() {
        return userSign;
    }

    public void setUserSign(Date userSign) {
        this.userSign = userSign;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userSex=" + userSex +
                ", userFollowing=" + userFollowing +
                ", userFollowers=" + userFollowers +
                ", userClass=" + userClass +
                ", userScore=" + userScore +
                ", userAvatar='" + userAvatar + '\'' +
                ", userSign=" + userSign +
                '}';
    }
}