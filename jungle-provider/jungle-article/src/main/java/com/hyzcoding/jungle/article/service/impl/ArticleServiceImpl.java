package com.hyzcoding.jungle.article.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyzcoding.jungle.article.service.ArticleService;
import com.hyzcoding.jungle.common.dao.ArticleMapper;
import com.hyzcoding.jungle.common.pojo.Article;
import com.hyzcoding.jungle.common.repo.ArticleRepository;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 〈文章service〉<br>
 * 〈 〉
 *
 * @author hyz
 * @date 2019/3/12
 * @since 1.0.0
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    ArticleRepository articleRepository;

    @Override
    public Article add(Article article) throws Exception {
        articleMapper.insert(article);
        //todo 调整插入代码结构
        //暂时使用人工设置
        article.setArticleLikes(0);
        article.setArticleViews(0);
        article.setArticleCreate(new Date());
        //结束人工设置
        articleRepository.save(article);
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
    public PageInfo<Article> findList(int pageNum, int pageSize, Article article) {
        PageHelper.startPage(pageNum, pageSize);
        List<Article> articles = articleMapper.selectList(article);
        PageInfo<Article> articlePageInfo = new PageInfo<>(articles);
        return articlePageInfo;
    }

    @Override
    public PageInfo<Article> findListEs(int pageNum, int pageSize, String keywords) {
        if (pageNum <= 0 || pageSize <= 0) {
            throw new NumberFormatException();
        }
        QueryBuilder builder =
                QueryBuilders.boolQuery()
                .should(QueryBuilders.fuzzyQuery("articleTitle",keywords))
                .should(QueryBuilders.fuzzyQuery("articleForum",keywords));
        FieldSortBuilder sort = SortBuilders.fieldSort("articleCreate").order(SortOrder.DESC);

        //pageNum在es中从0开始
        PageRequest page = PageRequest.of(pageNum - 1, pageSize);

        //2.构建查询
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //将搜索条件设置到构建中
        nativeSearchQueryBuilder.withQuery(builder);
        //将分页设置到构建中
        nativeSearchQueryBuilder.withPageable(page);
        //将排序设置到构建中
        nativeSearchQueryBuilder.withSort(sort);
        //生产NativeSearchQuery
        NativeSearchQuery query = nativeSearchQueryBuilder.build();

        Page<Article> articles = articleRepository.search(query);
        PageInfo<Article> articlePageInfo = new PageInfo<>();
        articlePageInfo.setList(articles.getContent());
        articlePageInfo.setPageNum(pageNum);
        articlePageInfo.setSize(pageSize);
        articlePageInfo.setTotal(articles.getTotalElements());
        return articlePageInfo;
    }

    @Override
    public Article modify(Article article) throws Exception {
        articleMapper.update(article);
        return article;
    }

    @Override
    public int remove(Integer id) throws Exception {
        articleMapper.deleteById(id);
        articleRepository.deleteById(id.toString());
        return 0;
    }
}
