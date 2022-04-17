package com.imooc.forest.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;


@Data
public class ForestInfoEntity {

    /**
     * 唯一key
     */
    private String key;

    /**
     * 请求url
     */
    private String url;

    /**
     * 请求类型, post 或者 get.
     */
    private String type;

    /**
     * 请求头, Map格式. ForestHeader
     */
    @JsonProperty("header")
    private Map<String, Object> headerMap;

    /**
     * 请求URL参数, map格式
     */
    @JsonProperty("urlParam")
    private Map<String, Object> urlParamMap;

    /**
     * 请求体
     */
    private Map<String, Object> requestBody;

    /**
     * connect timeout
     */
    private int connectTimeout;

    /**
     * read timeout
     */
    private int readTimeout;
}

