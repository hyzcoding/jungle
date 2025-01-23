package com.hyzcoding.jungle.user.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyzcoding.jungle.common.dao.UserInfoMapper;
import com.hyzcoding.jungle.common.dao.UserMapper;
import com.hyzcoding.jungle.common.pojo.User;
import com.hyzcoding.jungle.common.pojo.UserInfo;
import com.hyzcoding.jungle.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 
 * @author hyz
 *  2019/3/5
 * @version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public boolean register(User user, UserInfo userInfo) {
        userMapper.insert(user);
        userInfo.setUserId(user.getUserId());
        userInfoMapper.insert(userInfo);
        return true;
    }

    @Override
    public User login(User user) {
        user = userMapper.selectOne(user);
        if (user == null) {
            return null;
        } else {
            return user;
        }
    }

    @Override
    public User findById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public UserInfo findInfoById(Integer id) {
        return userInfoMapper.selectById(id);
    }

    @Override
    public int findCount(UserInfo userInfo) {
        return userInfoMapper.count(userInfo);
    }

    @Override
    public UserInfo findByWhere(UserInfo userInfo) {
        return userInfoMapper.selectOne(userInfo);
    }

    @Override
    public PageInfo<UserInfo> findList(int pageSize,int pageNum,UserInfo userInfo) {
        PageHelper.startPage(pageNum,pageSize);
        List<UserInfo> usersInfo = userInfoMapper.selectList(userInfo);
        return new PageInfo<>(usersInfo);
    }

    @Override
    public User modify(User user) {
        userMapper.update(user);
        return user;
    }

    @Override
    public UserInfo modifyInfo(UserInfo userInfo) {
        return userInfoMapper.update(userInfo) > 0 ? userInfo : userInfoMapper.selectById(userInfo.getUserId());

    }

    @Override
    public boolean remove(Integer id) {
        return userMapper.deleteById(id) > 0;
    }
}
