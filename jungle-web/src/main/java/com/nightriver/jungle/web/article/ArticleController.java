package com.nightriver.jungle.web.article;

import com.nightriver.jungle.article.api.ArticleService;
import com.nightriver.jungle.common.dto.Result;
import com.nightriver.jungle.common.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public String upload(@RequestParam("file") MultipartFile file,
                         @RequestParam("title") String title,
                         @RequestParam("forum") String forum) {
        //上传文件
        Result<String> resultUpload = articleService.upload(file);
        //添加文章
       if(resultUpload.getCode() == HttpStatus.OK){
           Article article = new Article();
           article.setArticleTitle(title);
           article.setArticleContent(resultUpload.getData());
           article.setArticleForum(Byte.valueOf(forum));
           Result result = articleService.add(article);
           if(result.getCode()!=HttpStatus.OK){
               return "提交失败";
           }
           return "提交成功";
       }
        return "提交失败";
    }


}
