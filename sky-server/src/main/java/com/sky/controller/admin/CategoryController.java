package com.sky.controller.admin;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: 超之皮
 * @date: 2025/12/27
 * @time: 13:15
 * @description:
 */
@RestController
@Slf4j
@Api("分类管理相关接口")
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    /**
     * 新增分类
     * @param categoryDTO 分类数据传输对象
     * @return 结果
     */
    @PostMapping
    @ApiOperation("新增分类")
    public Result<Object> save(@RequestBody CategoryDTO categoryDTO) {
        log.info("新增分类：{}", categoryDTO);
        categoryService.save(categoryDTO);
        return Result.success();
    }


    /**
     * 分类分页查询
     * @param categoryPageQueryDTO 分类分页查询数据传输对象
     * @return 结果
     */
    @GetMapping("/page")
    @ApiOperation("分类分页查询")
    public Result<PageResult> page(CategoryPageQueryDTO categoryPageQueryDTO) {
        log.info("分类分页查询：{}", categoryPageQueryDTO);
        PageResult pageResult = categoryService.pageQuery(categoryPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 启用或禁用分类
     * @param status 状态
     * @param id 分类id
     * @return 结果
     */
    @PostMapping("/status/{status}")
    @ApiOperation("启用或禁用分类")
    public Result<Object> startOrStop(@PathVariable int status, @RequestParam long id) {
        log.info("修改分类状态：{}, {}", status, id);
        categoryService.startOrStop(status, id);
        return Result.success();

    }

    /**
     * 根据类型查询分类列表
     * @param type 类型
     * @return 分类列表
     */
    @GetMapping("/list")
    @ApiOperation("根据类型查询分类列表")
    public Result<List<Category>> list(int type) {
        log.info("根据类型查询分类列表，类型：{}", type);
        List<Category> categories = categoryService.list(type);
        return Result.success(categories);
    }


    @PutMapping
    @ApiOperation("修改分类")
    public Result<Object> update(@RequestBody CategoryDTO categoryDTO) {
        log.info("修改分类：{}", categoryDTO);
        categoryService.update(categoryDTO);
        return Result.success();
    }

    /**
     * 根据id删除分类
     * @param id 分类id
     * @return 结果
     */
    @DeleteMapping
    @ApiOperation("根据id删除分类")
    public Result<Object> deleteById(long id) {
        log.info("根据id删除分类：{}", id);
        categoryService.deleteById(id);
        return Result.success();
    }

}
