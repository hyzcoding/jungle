package com.nightriver.jungle.article.controller;

import com.github.pagehelper.PageInfo;
import com.nightriver.jungle.article.service.ArticleService;
import com.nightriver.jungle.common.dto.Result;
import com.nightriver.jungle.common.pojo.Article;
import com.nightriver.jungle.common.util.JwtUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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

    @PostMapping("/upload")
    @RequiresRoles("USER")
    public Result upload(@RequestParam("file") MultipartFile file) {
        Result result = new Result();
        if (file.isEmpty()) {
            result.setCode(HttpStatus.BAD_REQUEST);
            result.setMessage("文件为空");
            return result;
        }

        // 获取文件名
        String fileName = file.getOriginalFilename();
        logger.info("上传的文件名为：" + fileName);

        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        logger.info("上传的后缀名为：" + suffixName);

        // 文件上传路径
        String filePath = "E://";

        // 解决中文问题，liunx下中文路径，图片显示问题
        // fileName = UUID.randomUUID() + suffixName;

        File dest = new File(filePath + fileName);

        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }

        try {
            file.transferTo(dest);
            result.setCode(HttpStatus.OK);
            result.setMessage("上传成功");
            result.setData("filePath + fileName");
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
        String userId = JwtUtil.getId(token);
        article.setUserId(Integer.valueOf(userId));
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
