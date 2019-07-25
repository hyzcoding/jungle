package com.nightriver.jungle.comments.controller;

import com.github.pagehelper.PageInfo;
import com.nightriver.jungle.comments.service.CommentsService;
import com.nightriver.jungle.common.dto.Result;
import com.nightriver.jungle.common.pojo.Comments;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * 〈评论相关〉<br>
 * 〈 〉
 *
 * @author hyz
 * @create 2019/5/17
 * @since 1.0.0
 */
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    CommentsService commentsService;
    /**
     * 上传评论
     * @param comments
     * @return
     */
    @PostMapping("/upload")
    @RequiresRoles("USER")
    public Result<Comments> upload(@RequestBody Comments comments){
        Result<Comments> result = new Result<>();
        commentsService.add(comments);
        result.setCode(HttpStatus.OK);
        result.setData(comments);
        return  result;
    }

    /**
     * 获取文章所有评论
     * @param parentId
     * @return
     */
    @GetMapping("/list")
    public Result<PageInfo<Comments>> getList(@RequestParam("id") int parentId){
        Result<PageInfo<Comments>> result = new Result<>();
        Comments comments = new Comments();
        comments.setParentId(parentId);
        PageInfo<Comments> commentsPageInfo = commentsService.findByWhere(comments);
        result.setCode(HttpStatus.OK);
        result.setData(commentsPageInfo);
        return result;
    }

    /**
     * 获取单个评论
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Comments> get(@PathVariable("id") int id){
        Result<Comments> result = new Result<>();
        Comments comments = commentsService.findById(id);
        result.setCode(HttpStatus.OK);
        result.setData(comments);
        return result;
    }

}
