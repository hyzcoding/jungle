package com.nightriver.jungle.web.user;

import com.nightriver.jungle.common.dto.Result;
import com.nightriver.jungle.common.pojo.User;
import com.nightriver.jungle.common.pojo.UserInfo;
import com.nightriver.jungle.user.api.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping(value = "index")
    public String index() {
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        return "/index";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "/login";
    }
    @PostMapping("/login")
    public String login(@RequestParam("userEml") String userEml,
                        @RequestParam("userPwd") String userPwd,
                        HttpSession session){
        User user = new User();
        user.setUserEml(userEml);
        user.setUserPwd(userPwd);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserEml(),
                user.getUserPwd());
        User userDb = null;
        String error = null;
        try {
            subject.login(token);
            userDb = (User) subject.getPrincipal();
            token.setRememberMe(true);
        } catch (UnknownAccountException e) {
            error = "用户名/密码错误";
        } catch (IncorrectCredentialsException e) {
            error = "用户名/密码错误";
        } catch (ExcessiveAttemptsException e) {
            // TODO: handle exception
            error = "登录失败多次，账户锁定10分钟";
        } catch (AuthenticationException e) {
            // 其他错误，比如锁定，如果想单独处理请单独catch处理
            error = "其他错误：" + e.getMessage();
        }
        if (error != null) {
            // 出错了，返回登录页面
            return "/web/login";
        }
        if (userDb != null) {
            Result<UserInfo> userInfoResult = userService.get(userDb.getUserId());
            UserInfo userInfo = userInfoResult.getData();
            session.setAttribute("userInfo",userInfo);
            return "redirect:/web/index";
        } else {
            return "/web/login";
        }
    }
}
