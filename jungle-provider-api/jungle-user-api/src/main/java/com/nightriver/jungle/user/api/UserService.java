package com.nightriver.jungle.user.api;

import com.nightriver.jungle.common.dto.Result;
import com.nightriver.jungle.common.pojo.User;
import com.nightriver.jungle.common.pojo.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈 〉
 *
 * @author hyz
 * @create 2019/3/5
 * @since 1.0.0
 */
@FeignClient("jungle-user")
public interface UserService {
    /**
     *
     * @param email
     * @return
     */
    @GetMapping("/vcode")
    Result vCode(String email);

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
    Result register(@RequestParam("name") String name,
                    @RequestParam("password") String password,
                    @RequestParam("email") String email,
                    @RequestParam("sex") Integer sex,
                    @RequestParam("vcode") String vcode,
                    @RequestParam("dbvcode") String dbvcode);

    /**
     *
     * @param user
     * @return
     */
    @PostMapping("/user/add")
    Result add(@RequestBody User user);

    /**
     * 用户登录
     *
     * @param user 用户账户信息
     * @return 返回结果
     */
    @PostMapping("/login")
    Result login(@RequestBody User user);

    /**
     * 用户退出登录
     *
     * @param user 用户id
     * @return 返回结果
     */
    @PostMapping("/logout")
    Result logout(@RequestBody User user);

    /**
     * 通过用户id查找用户详情
     *
     * @param id
     * @return
     */
    @GetMapping("/user/{id}")
    Result<UserInfo> get(@PathVariable("id") Integer id);



    /**
     * 查询符合条件的总数量
     *
     * @param userInfo 用户详情条件
     * @return
     */
    int findCount(UserInfo userInfo);

    /**
     *
     * @param keyWords
     * @param pageNum
     * @return
     */
    @GetMapping("/s/{keywords}/{pageNum}")
    Result getList(@PathVariable("keywords") String keyWords,
                          @PathVariable("pageNum") Integer pageNum);



    /**
     * 修改用户账号密码等
     *
     * @param user 用户
     * @return 结果
     */
    @PatchMapping("/user4pwd")
    Result modifyPwd(@RequestBody User user);

    /**
     * 修改用户金币等信息
     *
     * @param user 用户
     * @return 结果
     */
    @PatchMapping("/user")
    Result<User> modify(@RequestBody User user);

    /**
     * 修改用户详情信息
     *
     * @param userInfo 用户详细信息
     * @return 修改的用户详细信息
     */
    @PatchMapping("/userInfo")
    Result modifyInfo(@RequestBody UserInfo userInfo);

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return 结果
     */
    @DeleteMapping("/user")
    Result remove(@RequestParam("id") int id);
}
