package com.nightriver.jungle.article.api;

import com.nightriver.jungle.article.api.impl.ArticleHystrix;
import com.nightriver.jungle.common.config.JwtFeignInterceptor;
import com.nightriver.jungle.common.dto.Result;
import com.nightriver.jungle.common.pojo.Article;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 〈一句话功能简述〉<br>
 * 〈 〉
 *
 * @author hyz
 * @create 2019/3/12
 * @since 1.0.0
 */
@FeignClient(name="jungle-article",configuration = JwtFeignInterceptor.class,fallback = ArticleHystrix.class)
@Service
public interface ArticleService {
    /**
     * 上传文件
     * @param file
     * @return
     */
    @PostMapping(name = "/upload",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Result<String> upload(@RequestPart("file") MultipartFile file);

    /**
     * 添加
     *
     * @param article
     * @return
     */
    @PostMapping("/add")
    Result<String> add(@RequestBody Article article);

    /**
     * 刪除
     *
     * @param id
     * @return
     */
    @DeleteMapping("/del")
    Result remove(@RequestParam("id") int id);

    /**
     * 修改
     *
     * @param article
     * @return
     */
    @PatchMapping("/update")
    Result update(@RequestBody Article article);

    /**
     * 获取
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    Result get(@PathVariable("id") int id);

    /**
     * 条件查询
     *
     * @param pageSize 页面大小
     * @param pageNum 页数
     * @param keywords 关键字
     * @return
     */
    @GetMapping("/list")
    Result getList(@RequestParam("pageSize") int pageSize,
                   @RequestParam("pageNum") int pageNum,
                   @RequestParam("keywords") String keywords);

}
