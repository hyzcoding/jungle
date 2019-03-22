package com.nightriver.jungle.article.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nightriver.jungle.article.service.ArticleService;
import com.nightriver.jungle.common.dao.ArticleMapper;
import com.nightriver.jungle.common.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈 〉
 *
 * @author hyz
 * @create 2019/3/12
 * @since 1.0.0
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Override
    public Article add(Article article) throws Exception {
        articleMapper.insert(article);
        return article;
    }

    @Override
    public Article findById(Integer id) {
        return articleMapper.selectById(id);
    }

    @Override
    public int findCount(Article article) {
        return articleMapper.count(article);
    }

    @Override
    public Article findByWhere(Article article) {
        return articleMapper.selectOne(article);
    }

    @Override
    public PageInfo<Article> findList(int pageNum,int pageSize,Article article) {
        PageHelper.startPage(pageNum,pageSize);
        List<Article> articles = articleMapper.selectList(article);
        PageInfo<Article> articlePageInfo = new PageInfo<>(articles);
        return articlePageInfo;
    }

    @Override
    public Article modify(Article article) throws Exception {
        articleMapper.update(article);
        return article;
    }

    @Override
    public int remove(Integer id) throws Exception {
        return 0;
    }
}
