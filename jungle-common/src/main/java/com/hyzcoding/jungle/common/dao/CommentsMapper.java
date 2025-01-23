package com.hyzcoding.jungle.common.dao;

import com.hyzcoding.jungle.common.pojo.Comments;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 *     CommentsMapper
 * @author         hyz
 *      2019/3/2
 * @version        1.0
 **/
@Mapper
public interface CommentsMapper {
    /**
     * 插入评论
     * @param comments 插入评论对象
     * @return 受影响行数
     */
    int insert(Comments comments);

    /**
     * 条件查找评论
     * @param comments 包含条件的评论对象
     * @return 查找到的评论对象
     */
    Comments selectOne(Comments comments);

    /**
     * 通过Id查找评论
     * @param commentsId
     * @return
     */
    Comments selectById(@Param("commentsId") Integer commentsId);
    /**
     * 查询符合条件的总数
     * @param comments
     * @return
     */
    int count(Comments comments);

    /**
     * 条件查找评论列表
     * @param comments
     * @return
     */
    List<Comments> selectList(Comments comments);

    /**
     * 修改评论
     * @param comments
     * @return
     */
    int update(Comments comments);

    /**
     * 根据Id删除评论
     * @param commentsId
     * @return
     */
    int deleteById(@Param("commentsId") Integer commentsId);
}