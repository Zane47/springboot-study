package com.imooc.forest;


import com.dtflys.forest.annotation.*;
import com.dtflys.forest.http.ForestRequest;

import java.util.Map;


public interface ForestClient {


    /**
     * http request
     *
     * @param httpUrl         url
     * @param headerMap       请求头, map格式
     * @param type            请求类型
     * @param requestParamMap 请求URL参数, map格式
     *
     * @return response
     */
    @Request(url = "{httpUrl}", type = "{type}")
    ForestRequest<?> forestHttpRequest(
        @Var("httpUrl") String httpUrl,
        @Var("type") String type,
        @Header Map<String, Object> headerMap,
        @Query Map<String, Object> requestParamMap
    );
}
