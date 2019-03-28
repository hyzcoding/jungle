package com.nightriver.jungle.user.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.nightriver.jungle.common.dto.Result;
import com.nightriver.jungle.common.exception.NotExistException;
import com.nightriver.jungle.common.pojo.User;
import com.nightriver.jungle.common.pojo.UserInfo;
import com.nightriver.jungle.common.util.*;
import com.nightriver.jungle.user.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * 〈一句话功能简述〉<br>
 * 〈 〉
 *
 * @author hyz
 * @create 2019/3/6
 * @since 1.0.0
 */
@RestController()
@CrossOrigin
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserService userService;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private QiNiuUtil qiNiuUtil;

    private Subject subject;
    @Value("${spring.mail.from}")
    private String from;

    private static String imageView = "imageView2/1/w/50/h/50/format/jpg/q/75|imageslim";

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
            result.setCode(HttpStatus.OK);
            result.setData(code);
        } else {
            result.setMessage("邮箱不合法");
            result.setCode(HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    /**
     * 用户注册
     *
     * @param name     用户名
     * @param password 密码
     * @param email    邮箱地址
     * @param sex      性别
     * @param vcode    验证码
     * @return 返回结果
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
            result.setCode(HttpStatus.BAD_REQUEST);
        } else if (!ValidatorUtil.isUsername(name)) {
            result.setMessage("用户名不合法");
            result.setCode(HttpStatus.BAD_REQUEST);
        } else if (Strings.isNullOrEmpty(email)) {
            result.setMessage("请输入邮箱");
            result.setCode(HttpStatus.BAD_REQUEST);
        } else if (!ValidatorUtil.isEmail(email.trim())) {
            result.setMessage("邮箱不合法");
            result.setCode(HttpStatus.BAD_REQUEST);
        } else if (Strings.isNullOrEmpty(password)) {
            result.setMessage("请输入密码");
            result.setCode(HttpStatus.BAD_REQUEST);
        } else if (!ValidatorUtil.isPassword(password.trim())) {
            result.setMessage("密码不合法");
            result.setCode(HttpStatus.BAD_REQUEST);
        } else if (!dbvcode.equals(vcode.trim())) {
            result.setMessage("验证码错误");
            result.setCode(HttpStatus.BAD_REQUEST);
        } else {
            user.setUserEml(email);
            user.setUserPwd(password);
            userInfo.setUserName(name);
            userInfo.setUserSex(sex.byteValue());
            result.setCode(HttpStatus.OK);
            result.setMessage("注册成功！");
            result.setData("/login");
        }
        return result;
    }

    /**
     * 管理员添加用户
     *
     * @return
     */
    @PostMapping("/user/add")
    @RequiresRoles("ADMIN")
    public Result add(@RequestBody User user) {
        Result result = new Result();
        UserInfo userInfo = new UserInfo();
        try {
            userService.register(user, userInfo);
        } catch (Exception e) {
            e.printStackTrace();
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
    public Result<String> login(@RequestBody User user) {
        Result<String> result = new Result<>();
        result.setCode(HttpStatus.OK);
        result.setMessage("登录成功");
        User userDb = userService.login(user);
        if (userDb != null) {
            System.out.println(userDb.getUserId());
            String token = JwtUtil.sign(userDb);
            result.setData(token);
        }
        return result;
    }

    /**
     * 用户退出登录
     *
     * @param user 用户id
     * @return 返回结果
     */
    @PostMapping("/logout")
    public Result logout(@RequestBody User user) {
        Result result = new Result();
        SecurityUtils.getSubject().logout();
        result.setMessage("已退出登录");
        result.setCode(HttpStatus.OK);
        return result;
    }

    /**
     * 通过用户id查找用户详情
     *
     * @param id
     * @return
     */
    @GetMapping("/user/{id}")
    @RequiresRoles("USER")
    public Result<User> findById(@PathVariable("id") Integer id) {
        subject = SecurityUtils.getSubject();
        User loginUser = JwtUtil.checkRole(subject);
        if (loginUser.getUserRole().equals(Role.USER) && !loginUser.getUserId().equals(id)) {
            throw new ShiroException();
        }
        Result result = new Result();
        User user = userService.findById(id);
        result.setCode(HttpStatus.OK);
        result.setMessage("获取成功");
        result.setData(user);
        return result;
    }

    /**
     * 通过用户id查找用户详情
     *
     * @param id
     * @return
     */
    @GetMapping("/info/{id}")
    public Result<UserInfo> findInfoById(@PathVariable("id") Integer id) throws Exception {
        Result result = new Result();
        UserInfo userInfo = userService.findInfoById(id);
        if(userInfo == null){
            throw new NotExistException("用户不存在");
        }else {
            result.setCode(HttpStatus.OK);
            result.setMessage("获取成功");
            result.setData(userInfo);
        }
        return result;
    }

    /**
     * 模糊搜索
     *
     * @param keyWords
     * @param pageNum
     * @return
     */
    @GetMapping("/s/{keywords}/{pageNum}")
    public Result getList(@PathVariable("keywords") String keyWords,
                          @PathVariable("pageNum") Integer pageNum) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(keyWords);
        Result result = new Result();
        int pageSize = 5;
        PageInfo<UserInfo> userInfoPageInfo = userService.findList(pageSize, pageNum, userInfo);
        result.setCode(HttpStatus.OK);
        result.setMessage("获取成功");
        result.setData(userInfoPageInfo);
        return result;
    }

    /**
     * 修改用户详情信息
     *
     * @param userInfo 用户详细信息
     * @return 修改的用户详细信息
     */
    @PatchMapping("/info")
    @RequiresRoles("USER")
    public Result modifyInfo(@RequestBody UserInfo userInfo) {
        Result result = new Result();
        subject = SecurityUtils.getSubject();
        User user = JwtUtil.checkRole(subject);
        if (Role.USER.equals(user.getUserRole())) {
            userInfo.setUserId(user.getUserId());
        }
        try {
            userService.modifyInfo(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(HttpStatus.BAD_REQUEST);
            result.setMessage("修改失败");
        }
        //权限验证
        result.setMessage("修改成功");
        result.setData(userInfo);
        return result;
    }

    /**
     * 修改用户账号密码等
     *
     * @param user 用户
     * @return 结果
     */
    @PatchMapping("/user4pwd")
    @RequiresRoles("USER")
    public Result modifyPwd(@RequestBody User user) {
        Result result = new Result();
        subject = SecurityUtils.getSubject();
        User loginUser = JwtUtil.checkRole(subject);
        if (loginUser.getUserRole().equals(Role.USER) && !loginUser.getUserId().equals(user.getUserId())) {
            result.setMessage("没有权限");
            result.setCode(HttpStatus.UNAUTHORIZED);
            return result;
        }
        User userTmep = new User();
        userTmep.setUserId(user.getUserId());
        userTmep.setUserPwd(user.getUserPwd());
        try {
            result.setData(userService.modify(userTmep));
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("修改失败");
            result.setCode(HttpStatus.BAD_REQUEST);
        }
        //判断是否是用户,如果是则需要重新登录
        if (loginUser.getUserId().equals(userTmep.getUserId())) {
            subject.logout();
        }
        result.setMessage("修改成功");
        result.setCode(HttpStatus.OK);
        return result;
    }

    /**
     * 修改用户金币等信息
     *
     * @param user 用户
     * @return 结果
     */
    @PatchMapping("/user")
    @RequiresRoles("ADMIN")
    public Result<User> modify(@RequestBody User user) {
        Result<User> result = new Result<>();
        result.setMessage("修改成功");
        try {
            result.setData(userService.modify(user));
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(HttpStatus.BAD_REQUEST);
            result.setMessage("修改失败");
        }
        return result;
    }

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return 结果
     */
    @DeleteMapping("/user")
    @RequiresRoles("ADMIN")
    public Result remove(@RequestParam("id") int id) {
        Result result = new Result();
        try {
            if (userService.remove(id)) {
                result.setMessage("删除成功");
                result.setCode(HttpStatus.OK);
                result.setData(true);
            } else {
                result.setMessage("删除失败");
                result.setCode(HttpStatus.BAD_REQUEST);
                result.setData(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("删除失败");
            result.setCode(HttpStatus.BAD_REQUEST);
            result.setData(false);
        }
        return result;
    }

    /**
     * 上传用户头像
     *
     * @param avatar
     * @return
     */
    @PostMapping("/upload")
    @RequiresRoles("USER")
    public Result upload(@RequestParam("avatar") MultipartFile avatar) {
        subject = SecurityUtils.getSubject();
        User loginUser = JwtUtil.checkRole(subject);
        Result result = new Result();
        if (avatar.isEmpty()) {
            result.setCode(HttpStatus.BAD_REQUEST);
            result.setMessage("图片为空");
            return result;
        }
        try {
            //上传图片
            FileInputStream inputStream = (FileInputStream) avatar.getInputStream();
            // KeyUtil.getUniqueKey()生成图片的随机名
            String path = qiNiuUtil.uploadQNImg(inputStream, loginUser.getUserId() + "-" + KeyUtil.getUniqueKey(16));
            // 获取文件名
            result.setCode(HttpStatus.OK);
            result.setMessage("上传成功");
            result.setData(path + "?" + imageView);
            return result;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //TODO 上传文件
        //TODO 存储文件
        //TODO 返回链接
        result.setCode(HttpStatus.BAD_REQUEST);
        result.setMessage("上传失败");
        return result;
    }
}
