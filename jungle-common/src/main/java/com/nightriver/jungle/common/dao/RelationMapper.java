package com.nightriver.jungle.common.dao;

import com.nightriver.jungle.common.pojo.Relation;
import com.nightriver.jungle.common.pojo.Relation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
/**
 * @Description:    RelationMapper
 * @Author:         hyz
 * @CreateDate:     2019/3/2
 * @Version:        1.0
 **/
@Mapper
public interface RelationMapper {
    /**
     * 插入关系
     * @param relation 插入关系对象
     * @return 受影响行数
     */
    int insert(Relation relation);

    /**
     * 条件查找关系
     * @param relation 包含条件的关系对象
     * @return 查找到的关系对象
     */
    Relation selectOne(Relation relation);

    /**
     * 通过Id查找关系
     * @param relationId
     * @return
     */
    Relation selectById(Integer relationId);
    /**
     * 查询符合条件的总数
     * @param relation
     * @return
     */
    int count(Relation relation);

    /**
     * 条件查找关系列表
     * @param relation
     * @return
     */
    List<Relation> selectList(Relation relation);

    /**
     * 修改关系
     * @param relation
     * @return
     */
    int update(Relation relation);

    /**
     * 根据Id删除关系
     * @param relationId
     * @return
     */
    int deleteById(Integer relationId);
}