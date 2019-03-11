package com.nightriver.jungle.user.api;

import com.nightriver.jungle.common.pojo.User;
import com.nightriver.jungle.common.pojo.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * userService API
 */
@FeignClient("jungle-user")
public interface UserService {
    /**
     * 注册
     * @param user
     * @param userInfo
     * @return
     */
    boolean register(User user, UserInfo userInfo);

    /**
     * 登陆用户
     * @param user
     * @return
     */
    @PostMapping("/login")
    User login(User user);

    /**
     * 通过用户id查找用户详情
     * @param id
     * @return
     */
    @GetMapping("/user/{id}")
    UserInfo findById(@PathVariable("id") Integer id);

    /**
     * 查询符合条件的总数量
     * @param userInfo 用户详情条件
     * @return
     */
    int findCount(UserInfo userInfo);

    /**
     * 条件查找
     * @param userInfo
     * @return
     */
    UserInfo findByWhere(UserInfo userInfo);

    /**
     * 条件查找列表
     * @param userInfo
     * @return
     */
    List<UserInfo> findList(UserInfo userInfo);

    /**
     * 修改用户密码，增加用户金币等
     * @param user
     * @return
     */
    User modify(User user);

    /**
     * 用户修改信息
     * @param userInfo
     * @return
     */
    UserInfo modifyInfo(UserInfo userInfo);

    /**
     * 删除用户
     * @param id
     * @return
     */
    boolean remove(Integer id);
}
