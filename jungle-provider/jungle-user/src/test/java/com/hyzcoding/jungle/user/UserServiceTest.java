package com.hyzcoding.jungle.user;

import com.hyzcoding.jungle.common.pojo.User;
import com.hyzcoding.jungle.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * 〈一句话功能简述〉<br>
 * 〈 〉
 *
 * @author hyz
 * @date 2019/3/8
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    public void testLogin(){
        User user = new User();
        user.setUserEml("804762373@qq.com");
        user.setUserPwd("123456");
        userService.login(user);
    }

    @Test
    public void testFindById(){
        System.out.println(userService.findById(1));
    }
}
