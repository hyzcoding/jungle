package com.nightriver.jungle.article.controller;

import com.github.pagehelper.PageInfo;
import com.nightriver.jungle.article.service.ArticleService;
import com.nightriver.jungle.common.dto.Result;
import com.nightriver.jungle.common.pojo.Article;
import com.nightriver.jungle.common.pojo.User;
import com.nightriver.jungle.common.util.JwtUtil;
import com.nightriver.jungle.common.util.KeyUtil;
import com.nightriver.jungle.common.util.QiNiuUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;


/**
 * 〈ArticleController〉<br>
 * 〈 〉
 *
 * @author hyz
 * @create 2019/3/12
 * @since 1.0.0
 */
@RestController
@CrossOrigin
public class ArticleController {
    Logger logger = LoggerFactory.getLogger(ArticleController.class);
    @Autowired
    ArticleService articleService;
    @Autowired
    private QiNiuUtil qiNiuUtil;

    private Subject subject;

    @PostMapping("/upload")
    @RequiresRoles("USER")
    public Result upload(@RequestParam("file") MultipartFile file) {
        subject = SecurityUtils.getSubject();
        User loginUser = JwtUtil.checkRole(subject);
        Result result = new Result();
        if (file.isEmpty()) {
            result.setCode(HttpStatus.BAD_REQUEST);
            result.setMessage("内容为空");
            return result;
        }
        try {
            //上传图片
            FileInputStream inputStream = (FileInputStream) file.getInputStream();
            // KeyUtil.getUniqueKey()生成文章的随机名
            String path = qiNiuUtil.uploadQNImg(inputStream, loginUser.getUserId() + "-article-" + KeyUtil.getUniqueKey(16));
            // 获取文件名
            result.setCode(HttpStatus.OK);
            result.setData(path);
            return result;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //TODO 上传文件
        //TODO 存储文件
        //TODO 返回链接
        result.setCode(HttpStatus.BAD_REQUEST);
        result.setMessage("上传失败");
        return result;
    }

    @PostMapping("/add")
    @RequiresRoles("USER")
    public Result add(@RequestBody Article article) {
        String token = (String) SecurityUtils.getSubject().getPrincipal();
        int userId = JwtUtil.getId(token);
        article.setUserId(userId);
        Result result = new Result();
        //TODO 添加文件
        return result;
    }

    @DeleteMapping("/del")
    @RequiresRoles("MODERATOR")
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
    @RequiresRoles("USER")
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
    public Result getList(@RequestParam("pageSize") int pageSize,
                          @RequestParam("pageNum") int pageNum,
                          @RequestParam("keywords") String keywords){
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
