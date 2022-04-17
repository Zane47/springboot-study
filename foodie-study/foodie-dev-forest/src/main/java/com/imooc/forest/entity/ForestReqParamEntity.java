package com.imooc.forest.entity;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;


@Data
public class ForestReqParamEntity {

    /**
     * url
     */
    private String httpUrl;

    /**
     * 请求类型, post 或者 get.
     */
    private String type;

    /**
     * 请求头, Map格式. ForestHeader
     */
    private Map<String, Object> headerMap;

    /**
     * 请求URL参数, map格式
     */
    private Map<String, Object> requestParamMap;

    /**
     * 请求体, json格式. get请求不需要.
     */
    private String jsonBody;

    /**
     * connectTimeout
     */
    private int connectTimeout;

    /**
     * readTimeout
     */
    private int readTimeout;

    /**
     * 初始化
     */
    public ForestReqParamEntity() {
        this.headerMap = new HashMap<>();
        this.requestParamMap = new HashMap<>();
    }

    /**
     * 请求头参数
     *
     * @param key   key
     * @param value value
     */
    public void putHeaderMap(String key, Object value) {
        this.headerMap.put(key, value);
    }

    /**
     * 请求URL参数
     *
     * @param key   key
     * @param value value
     */
    public void putRequestParamMap(String key, Object value) {
        this.requestParamMap.put(key, value);
    }

}
