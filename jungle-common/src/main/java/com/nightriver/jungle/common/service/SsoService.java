package com.nightriver.jungle.common.service;

import com.nightriver.jungle.common.pojo.User;

/**
 * 〈一句话功能简述〉<br>
 * 〈 〉
 *
 * @author hyz
 * @create 2019/3/15
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
