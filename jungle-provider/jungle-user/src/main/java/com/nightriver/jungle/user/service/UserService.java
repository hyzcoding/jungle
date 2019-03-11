package com.nightriver.jungle.user.service;

import com.github.pagehelper.PageInfo;
import com.nightriver.jungle.common.pojo.User;
import com.nightriver.jungle.common.pojo.UserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:
 * @Author:         hyz
 * @CreateDate:     2019/3/2
 * @Version:        1.0
 **/
@Service
public interface UserService {
    /**
     * 注册
     * @param user
     * @param userInfo
     * @return
     */
    @Transactional(rollbackFor = {Exception.class})
    boolean register(User user, UserInfo userInfo) throws Exception;

    /**
     * 登陆用户
     * @param user
     * @return
     */
    User login(User user);

    /**
     * 通过用户id查找用户详情
     * @param id
     * @return
     */
    UserInfo findById(Integer id);

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
     * 获取用户详情列表
     * @param pageSize 数量
     * @param pageNum 页数
     * @param userInfo 查询对象
     * @return
     */
    PageInfo<UserInfo> findList(int pageSize,int pageNum,UserInfo userInfo);

    /**
     * 修改用户密码金币等
     * @param user
     * @return
     */
    @Transactional(rollbackFor = {Exception.class})
    User modify(User user) throws Exception;

    /**
     * 用户修改信息
     * @param userInfo
     * @return
     */
    @Transactional(rollbackFor = {Exception.class})
    UserInfo modifyInfo(UserInfo userInfo) throws Exception;

    /**
     * 删除用户
     * @param id
     * @return
     */
    @Transactional(rollbackFor = {Exception.class})
    boolean remove(Integer id) throws Exception;
}
