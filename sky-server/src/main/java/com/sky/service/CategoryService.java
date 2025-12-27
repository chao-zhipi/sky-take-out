package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;

import java.util.List;

/**
 * @author: 超之皮
 * @date: 2025/12/27
 * @time: 13:22
 * @description:
 */
public interface CategoryService {

    /**
     * 保存分类
     * @param categoryDTO 分类信息
     */
    void save(CategoryDTO categoryDTO);

    /**
     * 分类分页查询
     * @param categoryPageQueryDTO 分类分页查询数据传输对象
     * @return 结果
     */
    PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * 启用或禁用分类
     * @param status 状态
     * @param id 分类id
     */
    void startOrStop(int status, long id);

    /**
     * 根据类型查询分类列表
     * @param type 类型
     * @return 分类列表
     */
    List<Category> list(int type);

    /**
     * 更新分类信息
     * @param categoryDTO 分类数据传输对象
     */
    void update(CategoryDTO categoryDTO);

    /**
     * 根据id删除分类
     * 当前分类如果关联了菜品或者套餐，则不能删除
     * @param id 分类id
     */
    void deleteById(long id);
}
