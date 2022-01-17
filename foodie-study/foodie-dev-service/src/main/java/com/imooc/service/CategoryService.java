package com.imooc.service;

import com.imooc.pojo.Category;
import com.imooc.pojo.vo.CategoryVO;

import java.util.List;

/**
 * 首页分类相关服务
 */
public interface CategoryService {

    /**
     * 查询所有一级分类, type = 1
     */
    public List<Category> queryAllRootCategories();


    /**
     * 根据一级分类id查询子分类信息
     */
    public List<CategoryVO> getSubCategoryList(Integer rootCategoryId);


}
