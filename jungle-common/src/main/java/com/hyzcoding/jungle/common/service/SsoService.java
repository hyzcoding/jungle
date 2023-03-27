package com.hyzcoding.jungle.common.service;

import com.hyzcoding.jungle.common.pojo.User;

/**
 * 〈一句话功能简述〉<br>
 * 〈 〉
 *
 * @author hyz
 * @date 2019/3/15
 * @since 1.0.0
 */
public interface SsoService {
    /**
     * 登陆用户
     * @param user
     * @return
     */
    User login(User user);
}
