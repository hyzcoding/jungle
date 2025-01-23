package com.hyzcoding.jungle.common.pojo;

import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import java.io.Serializable;

/**
 *  用户实体
 * @author hyz
 *  2019/3/2
 * @version 1.0
 **/
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue
    private Integer userId;
    @Column(unique = true)
    private String userPwd;

    private String userEml;

    private String userRole;

    private Integer userCoin;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd == null ? null : userPwd.trim();
    }

    public String getUserEml() {
        return userEml;
    }

    public void setUserEml(String userEml) {
        this.userEml = userEml == null ? null : userEml.trim();
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public Integer getUserCoin() {
        return userCoin;
    }

    public void setUserCoin(Integer userCoin) {
        this.userCoin = userCoin;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userPwd='" + userPwd + '\'' +
                ", userEml='" + userEml + '\'' +
                ", userRole='" + userRole + '\'' +
                ", userCoin=" + userCoin +
                '}';
    }
}