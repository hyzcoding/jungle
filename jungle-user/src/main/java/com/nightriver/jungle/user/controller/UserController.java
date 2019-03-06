package com.nightriver.jungle.user.controller;

import com.nightriver.jungle.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈一句话功能简述〉<br>
 * 〈 〉
 *
 * @author hyz
 * @create 2019/3/6
 * @since 1.0.0
 */
@RestController()
public class UserController {
    @Autowired
    UserService userService;
    /**
     * 用户注册
     * @param name 用户名
     * @param password 密码
     * @param email 邮箱地址
     * @param sex 性别
     * @param vcode 验证码
     * @return
     */
    @PostMapping("/regist")
    public String regist(@RequestParam("name") String name,
                         @RequestParam("password") String password,
                         @RequestParam("email") String email,
                         @RequestParam("sex") Integer sex,
                         @RequestParam("vcode") String vcode){

        return "/index";
    }
}
