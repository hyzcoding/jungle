package com.nightriver.jungle.comments.service;

import com.github.pagehelper.PageInfo;
import com.nightriver.jungle.common.pojo.Comments;

/**
 *
 * @author hu
 * @date
 */
public interface CommentsService {
    /**
     * 添加评论
     * @param comments
     * @return
     */
    int add(Comments comments);

    /**
     * 通过id查询评论
     * @param id
     * @return
     */
    Comments findById(int id);

    /**
     * 条件查找评论
     * @param comments
     * @return
     */
    PageInfo<Comments> findByWhere(Comments comments);

}
