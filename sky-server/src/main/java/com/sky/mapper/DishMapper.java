package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author: 超之皮
 * @date: 2025/12/27
 * @time: 13:11
 * @description:
 */
@Mapper
public interface DishMapper {
    /**
     * 根据分类id统计菜品数量
     * @param id 分类id
     * @return 菜品数量
     */
    @Select("select count(*) from dish where category_id = #{id}")
    int countByCategoryId(long id);
}
