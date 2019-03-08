package com.nightriver.jungle.user.controller;

import com.google.common.base.Strings;
import com.nightriver.jungle.common.dto.Result;
import com.nightriver.jungle.common.pojo.User;
import com.nightriver.jungle.common.pojo.UserInfo;
import com.nightriver.jungle.common.util.MailUtil;
import com.nightriver.jungle.common.util.Role;
import com.nightriver.jungle.common.util.ValidatorUtil;
import com.nightriver.jungle.user.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    UserService userService;
    @Autowired
    private JavaMailSender mailSender;
//    @Value("${spring.mail.from}")
    private String from = "hyz951226";

    /**
     * 用户注册
     *
     * @param name     用户名
     * @param password 密码
     * @param email    邮箱地址
     * @param sex      性别
     * @param vcode    验证码
     * @return         返回结果
     */
    @PostMapping("/register")
    public Result register(@RequestParam("name") String name,
                           @RequestParam("password") String password,
                           @RequestParam("email") String email,
                           @RequestParam("sex") Integer sex,
                           @RequestParam("vcode") String vcode,
                           @RequestParam("dbvcode") String dbvcode) {
        Result result = new Result();
        //后端验证有效性
        User user = new User();
        UserInfo userInfo = new UserInfo();
        if (Strings.isNullOrEmpty(name)) {
            result.setMessage("请输入用户名");
        } else if (!ValidatorUtil.isUsername(name)) {
            result.setMessage("用户名不合法");
        } else if (Strings.isNullOrEmpty(email)) {
            result.setMessage("请输入邮箱");
        } else if (!ValidatorUtil.isEmail(email.trim())) {
            result.setMessage("邮箱不合法");
        } else if (Strings.isNullOrEmpty(password)) {
            result.setMessage("请输入密码");
        } else if (!ValidatorUtil.isPassword(password.trim())) {
            result.setMessage("密码不合法");
        } else if (!dbvcode.equals(vcode.trim())) {
            result.setMessage("验证码错误");
        } else {
            user.setUserEml(email);
            user.setUserPwd(password);
            userInfo.setUserName(name);
            userInfo.setUserSex(sex.byteValue());
            result.setMessage("注册成功！");
            result.setData("/login");
        }
        return result;
    }

    /**
     * 获取验证码
     *
     * @param email 邮箱
     * @return 返回结果
     */
    @GetMapping("/vcode")
    public Result vCode(String email) {
        Result result = new Result();
        //查询邮箱是否合法
        if (ValidatorUtil.isEmail(email)) {
            //邮箱是否注册查询
            String code = MailUtil.random(6);
            //发送验证码
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setTo(email);
            message.setSubject("主题：jungle 注册验证码");
            message.setText(code);
            mailSender.send(message);
            result.setMessage("发送验证码成功");
            result.setData(code);
        } else {
            result.setMessage("邮箱不合法");
        }
        return result;
    }

    /**
     * 用户登录
     *
     * @param user 用户账户信息
     * @return 返回结果
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        Result result = new Result();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserEml(),
                user.getUserPwd());
        String error = null;
        User userDb = null;
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
            result.setMessage(error);
            return null;
        }
        if (userDb != null) {
            UserInfo userInfo = userService.findById(userDb.getUserId());
            Map<String,Object> map = new HashMap<>(2);
            map.put("user", userDb);
            map.put("userInfo", userInfo);
            result.setMessage("获取成功");
            result.setData(map);
            return result;
        }

        return null;
    }

    /**
     * 用户退出登录
     * @param user 用户id
     * @return 返回结果
     */
    @PostMapping("/logout")
    public Result logout(@RequestBody User user){
        Result result = new Result();
        SecurityUtils.getSubject().logout();
        result.setData("已退出登录");
        return result;
    }

    /**
     * 获取用户详细信息
     *
     * @param userInfo 用户详情
     * @return 返回结果
     */
    @GetMapping("/user")
    public Result get(@RequestBody UserInfo userInfo) {
        Result result = new Result();
        userInfo = userService.findByWhere(userInfo);
        result.setMessage("获取成功");
        result.setData(userInfo);
        return result;
    }

    /**
     * 修改用户详情信息
     * @param userInfo 用户详细信息
     * @return 修改的用户详细信息
     */
    @PostMapping("/userInfo")
    @RequiresRoles({"USER", "MODERATOR", "ADMIN"})
    public Result modifyInfo(@RequestBody UserInfo userInfo) {
        Result result = new Result();
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if(Role.USER.equals(user.getUserRole())){
            userInfo.setUserId(user.getUserId());
        }
        userService.modifyInfo(userInfo);
        //权限验证
        result.setMessage("修改成功");
        result.setData(userInfo);
        return result;
    }

    /**
     * 修改用户账号密码等
     * @param user 用户
     * @return 结果
     */
    @PostMapping("/user4pwd")
    @RequiresRoles({"USER", "MODERATOR", "ADMIN"})
    public Result modifyPwd(@RequestBody User user) {
        Result result = new Result();
        if(Role.USER.equals(user.getUserRole())){
            user.setUserId(user.getUserId());
        }
        result.setMessage("修改成功");
        User userTmep = new User();
        userTmep.setUserId(user.getUserId());
        userTmep.setUserPwd(user.getUserPwd());
        result.setData(userService.modify(userTmep));
        Subject subject =SecurityUtils.getSubject();
        User userSecurity = (User) subject.getPrincipal();
        //判断是否是用户
        if(userSecurity.getUserId().equals(userTmep.getUserId())){
            subject.logout();
        }
        return result;
    }
    /**
     * 修改用户金币等信息
     * @param user 用户
     * @return 结果
     */
    @PostMapping("/user")
    @RequiresRoles({"USER", "MODERATOR", "ADMIN"})
    public Result modify(@RequestBody User user) {
        Result result = new Result();
        result.setMessage("修改成功");
        result.setData(userService.modify(user));
        return result;
    }

    /**
     * 删除用户
     * @param id 用户id
     * @return 结果
     */
    @DeleteMapping("/user")
    @RequiresRoles({"ADMIN"})
    public Result remove(@RequestParam("id") int id) {
        Result result = new Result();
        if(userService.remove(id)){
            result.setMessage("删除成功");
            result.setData(true);
        }else {
            result.setMessage("删除失败");
            result.setData(false);
        }
        return result;
    }
}
