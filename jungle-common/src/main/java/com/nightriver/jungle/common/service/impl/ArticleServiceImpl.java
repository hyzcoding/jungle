package com.nightriver.jungle.common.service.impl;

import com.nightriver.jungle.common.dao.ArticleMapper;
import com.nightriver.jungle.common.pojo.Article;
import com.nightriver.jungle.common.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author:         hyz
 * @CreateDate:     2019/3/2
 * @Version:        1.0
 **/
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleMapper articleMapper;

    @Override
    public Article add(Article article) {
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
    public List<Article> findList(Article article) {
        return articleMapper.selectList(article);
    }

    @Override
    public Article modify(Article article) {
        return articleMapper.update(article)>0?article:null;
    }

    @Override
    public int remove(Integer id) {
        return articleMapper.deleteById(id);
    }
}
