package com.nightriver.jungle.common.service.impl;

import com.nightriver.jungle.common.dao.UserMapper;
import com.nightriver.jungle.common.pojo.User;
import com.nightriver.jungle.common.service.SsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 〈一句话功能简述〉<br>
 * 〈 〉
 *
 * @author hyz
 * @create 2019/3/15
 * @since 1.0.0
 */
@Service
public class SsoServiceImpl implements SsoService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User login(User user) {
        user = userMapper.selectOne(user);
        if (user == null) {
            return null;
        } else {
            return user;
        }
    }
}
