package com.nightriver.jungle.common.service;

import com.nightriver.jungle.common.pojo.Article;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @Description:    文章处理Service
 * @Author:         hyz
 * @CreateDate:     2019/3/2
 * @Version:        1.0
 **/
@Service
public interface ArticleService {
    /**
     * 添加文章
     * @param article
     * @return
     */
    @Transactional(rollbackFor = {Exception.class})
    Article add(Article article);

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
    List<Article> findList(Article article);

    /**
     * 修改文章
     * @param article
     * @return
     */
    @Transactional(rollbackFor = {Exception.class})
    Article modify(Article article);

    /**
     * 删除文章
     * @param id
     * @return
     */
    @Transactional(rollbackFor = {Exception.class})
    int remove(Integer id);
}
