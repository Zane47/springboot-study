package com.imooc.mapper;

import com.imooc.pojo.vo.CategoryVO;

import java.util.List;

public interface CategoryMapperCustom {

    public List<CategoryVO> getSubCategoryList(Integer rootCategoryId);

}
