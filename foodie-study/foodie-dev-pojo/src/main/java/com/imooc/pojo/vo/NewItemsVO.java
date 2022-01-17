package com.imooc.pojo.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 最新商品VO
 */
@Getter
@Setter
public class NewItemsVO {
    private Integer rootCategoryId;
    private String rootCategoryName;
    private String slogan;
    private String categoryImage;
    private String backgroundColor;

    private List<SimpleItemVO> simpleItemList;
}
