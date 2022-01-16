package com.imooc.service;

import com.imooc.pojo.Category;

import java.util.List;

/**
 * 首页分类相关服务
 */
public interface CategoryService {

    /**
     * 查询所有一级分类, type = 1
     */
    public List<Category> queryAllRootCategories();

}
