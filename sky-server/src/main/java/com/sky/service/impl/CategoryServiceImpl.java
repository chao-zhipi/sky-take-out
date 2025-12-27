package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.context.BaseContext;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.CategoryMapper;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.result.PageResult;
import com.sky.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: 超之皮
 * @date: 2025/12/27
 * @time: 13:22
 * @description:
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private SetmealMapper setmealMapper;

    /**
     * 保存分类
     * @param categoryDTO 分类信息
     */
    @Override
    public void save(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);
        // 设置状态默认为禁用
        category.setStatus(StatusConstant.DISABLE);
        // 设置创建时间
        category.setCreateTime(LocalDateTime.now());
        // 设置更新时间
        category.setUpdateTime(LocalDateTime.now());
        // 设置创建人
        category.setCreateUser(BaseContext.getCurrentId());
        // 设置修改人
        category.setUpdateUser(BaseContext.getCurrentId());
        // 保存分类
        categoryMapper.insert(category);
    }

    /**
     * 分类分页查询
     * @param categoryPageQueryDTO 分类分页查询数据传输对象
     * @return 结果
     */
    @Override
    public PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO) {
        // 设置分页信息
        PageHelper.startPage(categoryPageQueryDTO.getPage(), categoryPageQueryDTO.getPageSize());
        // 执行分页查询
        Page<Category> page = categoryMapper.pageQuery(categoryPageQueryDTO);
        // 封装分页结果
        PageResult pageResult = new PageResult(page.getTotal(), page.getResult());
        page.close();
        return pageResult;
    }

    @Override
    public void startOrStop(int status, long id) {
        Category category = Category.builder()
                .id(id)
                .status(status).build();
        // 更新分类状态
        categoryMapper.update(category);
    }

    /**
     * 根据类型查询分类列表
     * @param type 类型
     * @return 分类列表
     */
    @Override
    public List<Category> list(int type) {
        return categoryMapper.list(type);
    }

    /**
     * 更新分类信息
     * @param categoryDTO 分类数据传输对象
     */
    @Override
    public void update(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);
        // 设置更新日期
        category.setUpdateTime(LocalDateTime.now());
        // 设置更新用户
        category.setUpdateUser(BaseContext.getCurrentId());
        // 更新分类信息
        categoryMapper.update(category);
    }

    /**
     * 根据id删除分类
     * 当前分类如果关联了菜品或者套餐，则不能删除
     * @param id 分类id
     */
    @Override
    public void deleteById(long id) {
        int countDish = dishMapper.countByCategoryId(id);
        int countSetMeal = setmealMapper.countByCategoryId(id);

        if (countDish > 0) {
            // 当前分类关联了菜品，不能删除，抛出异常
            throw new DeletionNotAllowedException(MessageConstant.CATEGORY_BE_RELATED_BY_DISH);
        }

        if (countSetMeal > 0) {
            // 当前分类关联了套餐，不能删除，抛出异常
            throw new DeletionNotAllowedException(MessageConstant.CATEGORY_BE_RELATED_BY_SETMEAL);
        }
        // 删除分类
        categoryMapper.deleteById(id);
    }
}
