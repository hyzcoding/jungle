package com.nightriver.jungle.web.article;

import com.nightriver.jungle.article.api.ArticleService;
import com.nightriver.jungle.common.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String details(){
        return "/details";
    }

    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file){
        Result result = articleService.upload(file);
        return result.getData().toString();
    }


}
