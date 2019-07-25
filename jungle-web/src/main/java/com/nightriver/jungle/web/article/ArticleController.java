package com.nightriver.jungle.web.article;

import com.nightriver.jungle.article.api.ArticleService;
import com.nightriver.jungle.common.dto.Result;
import com.nightriver.jungle.common.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 〈一句话功能简述〉<br>
 * 〈 〉
 *
 * @author hyz
 * @create 2019/3/21
 * @since 1.0.0
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @GetMapping("/details")
    public String details() {
        return "/details";
    }

    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") String file,
                         @RequestParam("title") String title,
                         @RequestParam("forum") String forum) {
        //上传文件
        //添加文章
           Article article = new Article();
           article.setArticleTitle(title);
           article.setArticleForum(forum);
           article.setArticleContent(file);
           Result result = articleService.add(article);
           if(result.getCode()!=HttpStatus.OK){
               return "提交失败";
           }
           return "提交成功";
    }
    @GetMapping("/all")
    public Result getList(int pageNum){
        return articleService.getList(10,pageNum,null);
    }
    @GetMapping("/user")
    public Result getListByUser(@RequestParam("pn")int pageNum,@RequestParam("userId") int userId){
        return articleService.getListByWhere(10,pageNum,userId,null);
    }
    @GetMapping("/forum")
    public Result getListByForum(@RequestParam("pn")int pageNum,@RequestParam("forum") Byte forum){
        return articleService.getListByWhere(10,pageNum,0,forum);
    }
}
