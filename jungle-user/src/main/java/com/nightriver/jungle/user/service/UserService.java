package com.nightriver.jungle.user.service;

import com.nightriver.jungle.common.pojo.User;
import com.nightriver.jungle.common.pojo.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author:         hyz
 * @CreateDate:     2019/3/2
 * @Version:        1.0
 **/
@Service
@FeignClient("jungle-user")
public interface UserService {
    /**
     * 注册用户
     * @param user
     * @return
     */
    @PostMapping("/regist")
    @Transactional(rollbackFor = {Exception.class})
    boolean regist(User user, UserInfo userInfo);

    /**
     * 登陆用户
     * @param user
     * @return
     */
    @PostMapping("/login")
    Map<String,Object> login(User user);

    /**
     * 通过用户id查找用户详情
     * @param id
     * @return
     */
    @GetMapping("/user/{id}")
    UserInfo findById(@PathVariable("id") Integer id);

    /**
     * 查询符合条件的总数量
     * @param userInfo
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
    @Transactional(rollbackFor = {Exception.class})
    User modify(User user);

    /**
     * 用户修改信息
     * @param userInfo
     * @return
     */
    @Transactional(rollbackFor = {Exception.class})
    UserInfo modifyInfo(UserInfo userInfo);

    /**
     * 删除用户
     * @param id
     * @return
     */
    @Transactional(rollbackFor = {Exception.class})
    boolean remove(Integer id);
}
