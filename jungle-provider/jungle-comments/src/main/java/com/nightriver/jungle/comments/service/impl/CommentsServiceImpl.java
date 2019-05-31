package com.nightriver.jungle.comments.service.impl;

import com.github.pagehelper.PageInfo;
import com.nightriver.jungle.comments.service.CommentsService;
import com.nightriver.jungle.common.pojo.Comments;

/**
 * 评论处理服务
 * 〈 〉
 *
 * @author hyz
 * @create 2019/5/17
 * @since 1.0.0
 */
public class CommentsServiceImpl implements CommentsService {
    @Override
    public int add(Comments comments) {
        //TODO 增加
        return 0;
    }

    @Override
    public Comments findById(int id) {
        //TODO 查询
        return null;
    }

    @Override
    public PageInfo<Comments> findByWhere(Comments comments) {
        //TODO 查询
        return null;
    }
}
