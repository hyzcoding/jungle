package com.nightriver.jungle.user.service.impl;

import com.nightriver.jungle.common.dao.UserInfoMapper;
import com.nightriver.jungle.common.dao.UserMapper;
import com.nightriver.jungle.common.pojo.User;
import com.nightriver.jungle.common.pojo.UserInfo;
import com.nightriver.jungle.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: hyz
 * @CreateDate: 2019/3/5
 * @Version: 1.0
 **/

public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public boolean regist(User user, UserInfo userInfo) {
        userMapper.insert(user);
        userInfo.setUserId(user.getUserId());
        userInfoMapper.insert(userInfo);
        return true;
    }

    @Override
    public Map<String, Object> login(User user) {
        Map<String, Object> map = new HashMap<>(2);
        user = userMapper.selectOne(user);
        if (user == null) {
            return null;
        } else {
            UserInfo userInfo = userInfoMapper.selectById(user.getUserId());
            map.put("user", user);
            map.put("userInfo", userInfo);
            return map;
        }
    }

    @Override
    public UserInfo findById(Integer id) {
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
    public List<UserInfo> findList(UserInfo userInfo) {
        return null;
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
