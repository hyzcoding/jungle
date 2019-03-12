package com.nightriver.jungle.article.controller;

import com.github.pagehelper.PageInfo;
import com.nightriver.jungle.article.service.ArticleService;
import com.nightriver.jungle.common.dto.Result;
import com.nightriver.jungle.common.pojo.Article;
import com.nightriver.jungle.common.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * 〈一句话功能简述〉<br>
 * 〈 〉
 *
 * @author hyz
 * @create 2019/3/12
 * @since 1.0.0
 */
@RestController
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @PostMapping("/upload")
    @RequiresRoles({"USER", "MODERATOR", "ADMIN"})
    public Result upload() {
        Result result = new Result();
        //TODO  上传文件
        return result;
    }

    @PostMapping("/add")
    @RequiresRoles({"USER", "MODERATOR", "ADMIN"})
    public Result add(@RequestBody Article article) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        article.setUserId(user.getUserId());
        Result result = new Result();
        //TODO 添加文件
        return result;
    }

    @DeleteMapping("/del")
    @RequiresRoles({"MODERATOR", "ADMIN"})
    public Result remove(@RequestParam("id") int id) {
        Result result = new Result();
        try {
            articleService.remove(id);
            result.setMessage("删除成功");
            result.setCode(HttpStatus.OK);
            result.setData(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("删除失败");
            result.setCode(HttpStatus.BAD_REQUEST);
            result.setData(false);
        }
        return result;
    }

    @PatchMapping("/update")
    @RequiresRoles({"USER", "MODERATOR", "ADMIN"})
    public Result update(@RequestBody Article article) {
        Result result = new Result();
        try {
            articleService.modify(article);
            result.setCode(HttpStatus.OK);
            result.setMessage("修改成功");
            result.setData(article);
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(HttpStatus.BAD_REQUEST);
            result.setMessage("修改失败");
        }
        return result;
    }

    @GetMapping("/{id}")
    public Result get(@PathVariable("id") int id) {
        Result result = new Result();
        Article article = articleService.findById(id);
        result.setCode(HttpStatus.OK);
        result.setMessage("获取成功");
        result.setData(article);
        return result;
    }

    @GetMapping("/list")
    public Result getList(int pageSize, int pageNum, String keywords) {
        Result result = new Result();
        Article article = new Article();
        article.setArticleTitle(keywords);
        PageInfo<Article> articlePageInfo = articleService.findList(pageNum, pageSize, article);
        result.setData(articlePageInfo);
        result.setCode(HttpStatus.OK);
        result.setMessage("获取成功");
        return result;
    }
}
