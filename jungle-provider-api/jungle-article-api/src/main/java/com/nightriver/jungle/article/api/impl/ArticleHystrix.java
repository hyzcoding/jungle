package com.nightriver.jungle.article.api.impl;

import com.nightriver.jungle.article.api.ArticleService;
import com.nightriver.jungle.common.dto.Result;
import com.nightriver.jungle.common.pojo.Article;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * 〈一句话功能简述〉<br>
 * 〈 〉
 *
 * @author hyz
 * @create 2019/4/2
 * @since 1.0.0
 */
@Component
public class ArticleHystrix implements ArticleService {
    @Override
    public Result<String> upload(MultipartFile file) {
        return Result.fail(HttpStatus.SERVICE_UNAVAILABLE,"服务器异常");
    }

    @Override
    public Result<String> add(Article article) {
        return Result.fail(HttpStatus.SERVICE_UNAVAILABLE,"服务器异常");
    }

    @Override
    public Result remove(int id) {
        return Result.fail(HttpStatus.SERVICE_UNAVAILABLE,"服务器异常");
    }

    @Override
    public Result update(Article article) {
        return Result.fail(HttpStatus.SERVICE_UNAVAILABLE,"服务器异常");
    }

    @Override
    public Result get(int id) {
        return Result.fail(HttpStatus.SERVICE_UNAVAILABLE,"服务器异常");
    }

    @Override
    public Result getList(int pageSize, int pageNum, String keywords) {
        return Result.fail(HttpStatus.SERVICE_UNAVAILABLE,"服务器异常");
    }

    @Override
    public Result getListByWhere(int pageSize, int pageNum, int userId, Byte forum) {
        return Result.fail(HttpStatus.SERVICE_UNAVAILABLE,"服务器异常");
    }
}
