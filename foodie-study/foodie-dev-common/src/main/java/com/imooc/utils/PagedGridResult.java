package com.imooc.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Title: PagedGridResult.java
 * @Package com.imooc.utils
 * @Description: 用来返回分页Grid的数据格式
 * Copyright: Copyright (c) 2019
 */
@Getter
@Setter
public class PagedGridResult {
    // 当前页数
    private int page;
    // 总页数
    private int total;
    // 总记录数
    private long records;
    // 每行显示的内容
    private List<?> rows;
}
