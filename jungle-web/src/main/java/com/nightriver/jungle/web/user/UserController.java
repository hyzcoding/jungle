package com.nightriver.jungle.web.user;

import com.google.common.base.Strings;
import com.nightriver.jungle.common.dto.Result;
import com.nightriver.jungle.common.pojo.User;
import com.nightriver.jungle.user.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * 〈一句话功能简述〉<br>
 * 〈 〉
 *
 * @author hyz
 * @create 2019/3/11
 * @since 1.0.0
 */
@Controller
public class UserController {
    private final String TOKEN_SESSION = "token";
    @Autowired
    UserService userService;

    @RequestMapping(value = {"index","/"})
    public String index() {
        return "/index";
    }
    @RequestMapping(value = "editor")
    public String editor(HttpSession session) {
        //判断是否登录

        //如果登录
        if(!Strings.isNullOrEmpty(session.getAttribute(TOKEN_SESSION).toString())){
            return "/editor";
        }

        //如果未登录
        return "redirect:/index";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("userEml") String userEml,
                        @RequestParam("userPwd") String userPwd,
                        HttpSession session) {
        User user = new User();
        user.setUserEml(userEml);
        user.setUserPwd(userPwd);
        String token = userService.login(user).getData();
        session.setAttribute(TOKEN_SESSION,token);
        return "redirect:/index";
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    public Result<User> findById(@PathVariable("id") Integer id){
        return userService.get(id);
    }
}
