package com.imooc.pojo.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * 用于展示商品评价数量的vo
 */
@Getter
@Setter
public class CommentLevelCountsVO {
    private Integer totalCounts;
    private Integer goodCounts;
    private Integer normalCounts;
    private Integer badCounts;
}
