package com.hyzcoding.jungle.article.service;

import com.github.pagehelper.PageInfo;
import com.hyzcoding.jungle.common.pojo.Article;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface ArticleService {

    /**
     * 添加文章
     * @param article
     * @return
     */
    @Transactional(rollbackFor = {Exception.class})
    Article add(Article article) throws Exception;

    /**
     * 通过文章id查找文章
     * @param id
     * @return
     */
    Article findById(Integer id);

    /**
     * 查询总数量
     * @param article
     * @return
     */
    int findCount(Article article);

    /**
     * 条件查找
     * @param article
     * @return
     */
    Article findByWhere(Article article);

    /**
     * 条件查找列表
     * @param article
     * @return
     */
    PageInfo<Article> findList(int pageNum,int pageSize,Article article);

    /**
     * 搜索
     * @param pageNum
     * @param pageSize
     * @param keywords
     * @return
     */
    PageInfo<Article> findListEs(int pageNum, int pageSize, String keywords);

    /**
     * 修改文章
     * @param article
     * @return
     */
    @Transactional(rollbackFor = {Exception.class})
    Article modify(Article article) throws Exception;

    /**
     * 删除文章
     * @param id
     * @return
     */
    @Transactional(rollbackFor = {Exception.class})
    int remove(Integer id) throws Exception;
}
