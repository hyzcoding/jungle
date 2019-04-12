package com.nightriver.jungle.common.repo;

import com.nightriver.jungle.common.pojo.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 *
 *
 */
@Component
public interface ArticleRepository extends ElasticsearchRepository<Article,String> {
    /**
     * 通过id查找
     * @param s
     * @return
     */
    @Override
    Optional<Article> findById(String s);

    /**
     * 通过文章标题或板块查找
     * @param s
     * @return
     */
    List<Article> findArticlesByArticleTitle(String s);
}
