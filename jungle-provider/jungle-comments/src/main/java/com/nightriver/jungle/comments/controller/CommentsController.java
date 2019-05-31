package com.nightriver.jungle.comments.controller;

import com.github.pagehelper.Page;
import com.nightriver.jungle.common.dto.Result;
import com.nightriver.jungle.common.pojo.Comments;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;

/**
 * 〈一句话功能简述〉<br>
 * 〈 〉
 *
 * @author hyz
 * @create 2019/5/17
 * @since 1.0.0
 */
@RequestMapping("/comments")
public class CommentsController {
    /**
     * 上传评论
     * @param comments
     * @return
     */
    @PostMapping("/upload")
    @RequiresRoles("USER")
    public Result<String> upload(@RequestBody Comments comments){
        Result<String> result = new Result<>();
        //TODO 存储操作
        return  result;
    }

    /**
     * 获取文章所有评论
     * @param parentId
     * @return
     */
    @GetMapping("/list")
    public Result<Page<Comments>> getList(@RequestParam("id") int parentId){
        Result<Page<Comments>> result = new Result<>();
        //TODO 查询操作
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
        //TODO 查询操作
        return result;
    }

}
