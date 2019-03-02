package com.nightriver.jungle.common.dao;

import com.nightriver.jungle.common.pojo.UserInfo;
import com.nightriver.jungle.common.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserInfoMapper {
    /**
     * 插入用户信息
     * @param userInfo 插入用户信息对象
     * @return 受影响行数
     */
    int insert(UserInfo userInfo);

    /**
     * 条件查找用户信息
     * @param userInfo 包含条件的用户信息对象
     * @return 查找到的用户信息对象
     */
    UserInfo selectOne(UserInfo userInfo);

    /**
     * 通过Id查找用户信息
     * @param userId
     * @return
     */
    UserInfo selectById(Integer userId);
    /**
     * 查询符合条件的总数
     * @param userInfo
     * @return
     */
    int count(UserInfo userInfo);

    /**
     * 条件查找用户信息列表
     * @param userInfo
     * @return
     */
    List<UserInfo> selectList(UserInfo userInfo);

    /**
     * 修改用户信息
     * @param userInfo
     * @return
     */
    int update(UserInfo userInfo);

    /**
     * 根据Id删除用户信息
     * @param userId
     * @return
     */
    int deleteById(Integer userId);
}