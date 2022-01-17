package com.imooc.service.impl;

import com.imooc.mapper.CategoryMapper;
import com.imooc.mapper.CategoryMapperCustom;
import com.imooc.pojo.Category;
import com.imooc.pojo.vo.CategoryVO;
import com.imooc.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.swing.*;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryMapperCustom categoryMapperCustom;

    /**
     * 查询所有一级分类, type = 1
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Category> queryAllRootCategories() {
        Example example = new Example(Category.class);

        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("type", "1");

        return categoryMapper.selectByExample(example);
    }


    /**
     * 根据一级分类id查询子分类信息
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<CategoryVO> getSubCategoryList(Integer rootCategoryId) {
        return categoryMapperCustom.getSubCategoryList(rootCategoryId);
    }


}
