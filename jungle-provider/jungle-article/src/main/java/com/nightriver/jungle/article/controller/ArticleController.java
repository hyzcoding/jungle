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
    /**
     * 添加文件信息至数据库
     * @param article 文章信息
     * @return 结果
     * @throws Exception
     */
    @PostMapping("/add")
    @RequiresRoles("USER")
    public Result add(@RequestBody Article article) throws Exception {
        String token = (String) SecurityUtils.getSubject().getPrincipal();
        int userId = JwtUtil.getId(token);
        article.setUserId(userId);
        articleService.add(article);
        Result result = new Result();
        result.setCode(HttpStatus.OK);
        result.setMessage("添加成功");
        return result;
    }

    /**
     * 删除文章
     * @param id 文章id
     * @return 删除结果
     */
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

    /**
     * 修改文章
     * @param article 修改文章信息
     * @return 结果
     */
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

    /**
     * 根据id获取文章
     * @param id 文章id
     * @return 结果
     */
    @GetMapping("/{id}")
    public Result get(@PathVariable("id") int id) {
        Result result = new Result();
        Article article = articleService.findById(id);
        result.setCode(HttpStatus.OK);
        result.setMessage("获取成功");
        result.setData(article);
        return result;
    }

    /**
     * 根据关键词获取文章列表
     * @param pageSize 页面显示数量
     * @param pageNum 页数
     * @param keywords 关键词
     * @return 结果
     */
    @GetMapping("/s")
    public Result getList(@RequestParam("pageSize") int pageSize,
                          @RequestParam("pageNum") int pageNum,
                          @RequestParam("keywords") String keywords){
        Result result = new Result();
        PageInfo<Article> articlePageInfo = articleService.findListEs(pageNum, pageSize, keywords);
        result.setData(articlePageInfo);
        result.setCode(HttpStatus.OK);
        result.setMessage("获取成功");
        return result;
    }

    /**
     * 条件查找文章列表
     * @param pageSize 页面显示数量
     * @param pageNum 页数
     * @param userId 用户id
     * @param forum 板块id
     * @return 结果
     */
    @GetMapping("/list")
    public Result<PageInfo> getListByWhere(@RequestParam("pageSize") int pageSize,
                                           @RequestParam("pageNum") int pageNum,
                                           @RequestParam(name = "userId",required = false) int userId,
                                           @RequestParam(name = "forum",required = false) Byte forum){
        Result result = new Result();
        Article article = new Article();
        article.setArticleForum(forum);
        if(userId == 0){
            article.setUserId(null);
        }
        PageInfo<Article> articlePageInfo = articleService.findList(pageNum, pageSize, article);
        result.setData(articlePageInfo);
        result.setMessage("获取成功");
        result.setCode(HttpStatus.OK);
        return result;
    }

}
