package com.nightriver.jungle.common.dao;

import com.nightriver.jungle.common.pojo.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:    文章Mapper
 * @Author:         hyz
 * @CreateDate:     2019/3/1
 * @Version:        1.0
 **/
@Mapper
public interface ArticleMapper {
    /**
     * 插入文章
     * @param article 插入文章对象
     * @return 受影响行数
     */
    int insert(Article article);

    /**
     * 条件查找文章
     * @param article 包含条件的文章对象
     * @return 查找到的文章对象
     */
    Article selectOne(Article article);

    /**
     * 通过Id查找文章
     * @param articleId
     * @return
     */
    Article selectById(@Param("articleId") Integer articleId);
    /**
     * 查询符合条件的总数
     * @param article
     * @return
     */
    int count(Article article);

    /**
     * 条件查找文章列表
     * @param article
     * @return
     */
    List<Article> selectList(Article article);

    /**
     * 修改文章
     * @param article
     * @return
     */
    int update(Article article);

    /**
     * 根据Id删除文章
     * @param articleId
     * @return
     */
    int deleteById(@Param("articleId") Integer articleId);
}