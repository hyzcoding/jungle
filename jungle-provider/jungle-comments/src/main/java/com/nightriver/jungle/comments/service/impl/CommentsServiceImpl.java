package com.nightriver.jungle.comments.service.impl;

import com.github.pagehelper.PageInfo;
import com.nightriver.jungle.comments.service.CommentsService;
import com.nightriver.jungle.common.dao.CommentsMapper;
import com.nightriver.jungle.common.pojo.Comments;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 评论处理服务
 * 〈 〉
 *
 * @author hyz
 * @create 2019/5/17
 * @since 1.0.0
 */
public class CommentsServiceImpl implements CommentsService {
    @Autowired
    CommentsMapper commentsMapper;

    @Override
    public int add(Comments comments) {
        return commentsMapper.insert(comments);
    }

    @Override
    public Comments findById(int id) {
        return commentsMapper.selectById(id);
    }

    @Override
    public PageInfo<Comments> findByWhere(Comments comments) {
        PageInfo<Comments> commentsPageInfo = new PageInfo<>();
        //TODO 分页
        return null;
    }
}
