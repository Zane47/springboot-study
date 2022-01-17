package com.imooc.service;

import com.imooc.pojo.Category;
import com.imooc.pojo.vo.CategoryVO;
import com.imooc.pojo.vo.NewItemsVO;

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


    /**
     * 查询首页每个一级分类下的6条最新商品数据
     */
    public List<NewItemsVO> getSixNewItemsLazy(Integer rootCategoryId);

}
