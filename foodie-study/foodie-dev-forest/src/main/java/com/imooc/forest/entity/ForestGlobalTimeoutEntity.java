package com.imooc.forest.entity;

import lombok.Data;


@Data
public class ForestGlobalTimeoutEntity {
    /**
     * 全局connectTimeout
     */
    private int globalConnectTimeout;

    /**
     * 全局readTimeout
     */
    private int globalReadTimeout;
}
