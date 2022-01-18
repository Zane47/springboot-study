package com.imooc.enums;

/**
 * 商品评价等级 枚举
 */
public enum CommentLevel {
    GOOD(1, "good comment"),
    NORMAL(2, "normal comment"),
    BAD(3, "bad comment");

    public final Integer type;
    public final String value;

    CommentLevel(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
