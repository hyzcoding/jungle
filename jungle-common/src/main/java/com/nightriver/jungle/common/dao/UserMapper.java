package com.nightriver.jungle.common.dao;

import com.nightriver.jungle.common.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
/**
 * @Description:    用户Dao
 * @Author:         hyz
 * @CreateDate:     2019/3/2
 * @Version:        1.0
 **/
@Mapper
public interface UserMapper {
    /**
     * 插入用户
     * @param user 插入用户对象
     * @return 受影响行数
     */
    int insert(User user);

    /**
     * 条件查找用户
     * @param user 包含条件的用户对象
     * @return 查找到的用户对象
     */
    User selectOne(User user);

    /**
     * 通过Id查找用户
     * @param userId
     * @return
     */
    User selectById(Integer userId);
    /**
     * 查询符合条件的总数
     * @param user
     * @return
     */
    int count(User user);

    /**
     * 条件查找用户列表
     * @param user
     * @return
     */
    List<User> selectList(User user);

    /**
     * 修改用户
     * @param user
     * @return
     */
    int update(User user);

    /**
     * 根据Id删除用户
     * @param userId
     * @return
     */
    int deleteById(Integer userId);
}