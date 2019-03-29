package com.nightriver.jungle.web.user;

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
    @Autowired
    UserService userService;

    @RequestMapping(value = {"index","/"})
    public String index() {
        return "/index";
    }
    @RequestMapping(value = "editor")
    public String editor() {
        return "/editor";
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
        session.setAttribute("token",token);
        return "redirect:/index";
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    public Result<User> findById(@PathVariable("id") Integer id){
        return userService.get(id);
    }
}
