package com.nightriver.jungle.web.article;

import com.alibaba.fastjson.JSONObject;
import com.nightriver.jungle.article.api.ArticleService;
import com.nightriver.jungle.common.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

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

    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") String file){
        Result result = new Result();
        System.out.println(file);
//        Result result = articleService.upload(file);
        Map map = new HashMap(2);
        map.put("errno",0);
        map.put("data",result.getData());
        JSONObject json = new JSONObject(map);
        return result.getData().toString();
    }


}
