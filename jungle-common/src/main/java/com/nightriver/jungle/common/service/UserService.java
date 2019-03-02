package com.nightriver.jungle.common.service;

import com.nightriver.jungle.common.pojo.Users;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description:
 * @Author:         hyz
 * @CreateDate:     2019/3/2
 * @Version:        1.0
 **/
public interface UserService {
    /**
     * 添加用户
     * @param user
     * @return
     */
    @Transactional(rollbackFor = {Exception.class})
    Users add(Users user);

    /**
     * 通过用户id查找用户
     * @param id
     * @return
     */
    Users findById(Integer id);

    /**
     * 查询总数量
     * @param user
     * @return
     */
    int findCount(Users user);

    /**
     * 条件查找
     * @param user
     * @return
     */
    Users findByWhere(Users user);

    /**
     * 条件查找列表
     * @param user
     * @return
     */
    List<Users> findList(Users user);

    /**
     * 修改用户
     * @param user
     * @return
     */
    @Transactional(rollbackFor = {Exception.class})
    Users modify(Users user);

    /**
     * 删除用户
     * @param id
     * @return
     */
    @Transactional(rollbackFor = {Exception.class})
    int remove(Integer id);
}
