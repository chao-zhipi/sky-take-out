package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author: 超之皮
 * @date: 2025/12/27
 * @time: 13:10
 * @description:
 */
@Mapper
public interface CategoryMapper {


    /**
     * 分类分页查询
     * @param categoryPageQueryDTO 分类分页查询数据传输对象
     * @return 分页结果
     */
    Page<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * 插入分类
     * @param category 分类信息
     */
    @Insert("insert into category " +
            "(type, name, sort, status, create_time, " +
            "update_time, create_user, update_user) " +
            "values (#{type}, #{name}, #{sort}, #{status}, #{createTime}," +
            "#{updateTime}, #{createUser}, #{updateUser})")
    void insert(Category category);

    /**
     * 通用更新方法
     * @param category 分类信息
     */
    void update(Category category);

    /**
     * 根据类型查询分类列表
     * @param type 类型
     * @return 分类列表
     */
    List<Category> list(int type);

    /**
     * 根据id删除分类
     * @param id 分类id
     */
    @Delete("delete from category where id = #{id}")
    void deleteById(long id);
}
