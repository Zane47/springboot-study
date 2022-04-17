package com.imooc.forest;

import com.dtflys.forest.backend.ContentType;
import com.dtflys.forest.http.ForestRequest;
import com.imooc.forest.entity.ForestReqParamEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@Component
public class ForestWebUtils {

    @Resource
    private ForestClient forestClient;



    /**
     * http request
     *
     * @param key           qconfig中配置key, 可为空
     * @param entity        entity
     * @param responseClass response class
     * @param <T>           T
     * @return response
     */
    public <T> T httpRequest(String key, ForestReqParamEntity entity, Class<T> responseClass) {
        try {
            if (null == entity) {
                entity = new ForestReqParamEntity();
            }

            if (check(entity)) {
                return null;
            }

            ForestRequest<?> forestRequest = forestClient.forestHttpRequest(
                entity.getHttpUrl(), entity.getType(), entity.getHeaderMap(), entity.getRequestParamMap());

            // 组装其他的参数
            assembleOtherParam(key, forestRequest, entity);

            // 不合理参数警告
            alertInappropriateParameter(forestRequest);

            return (T) forestRequest.execute(/*responseClass*/);
        } catch (Exception e) {
            log.error("error when http request by forest", e);
        }
        return null;
    }





    /**
     * 配置的其他参数
     * 暂只支持json的请求体
     *
     * @param key           key
     * @param forestRequest forestRequest
     * @param entity        entity
     */
    private void assembleOtherParam(String key, ForestRequest<?> forestRequest, ForestReqParamEntity entity) {
        // ------------------------ entity ------------------------
        // jsonBody
        final String jsonBody = entity.getJsonBody();
        if (!StringUtils.isBlank(jsonBody)) {
            forestRequest.setContentType(ContentType.APPLICATION_JSON);
            forestRequest.addBody(jsonBody);
        }
        // timeout
        assembleTimeout(forestRequest, entity.getConnectTimeout(), entity.getReadTimeout());

    }

    /**
     * 不合理参数警告
     *
     * @param forestRequest 请求对象
     */
    private void alertInappropriateParameter(ForestRequest<?> forestRequest) {
        // ------------------------ timeout ------------------------
        /*if (forestRequest.getConnectTimeout() <= 0 || forestRequest.getReadTimeout() <= 0) {
            log.warn("You had better specify connectTimeout and readTimeout in either qconfig or code.");
        }*/
    }

    /**
     * timeout属性拼装
     *
     * @param forestRequest      请求对象
     * @param tempConnectTimeout 候选connectTimeout
     * @param tempReadTimeout    候选readTimeout
     */
    private void assembleTimeout(ForestRequest<?> forestRequest, int tempConnectTimeout, int tempReadTimeout) {
        /*if (forestRequest.getConnectTimeout() <= 0 && tempConnectTimeout > 0) {
            forestRequest.setConnectTimeout(tempConnectTimeout);
        }
        if (forestRequest.getReadTimeout() <= 0 && tempReadTimeout > 0) {
            forestRequest.setReadTimeout(tempReadTimeout);
        }*/
    }

    /**
     * 校验参数, 如果为空需要初始化, forest参数不能为null
     *
     * @param entity entity
     * @return check
     */
    private boolean check(ForestReqParamEntity entity) {
        if (StringUtils.isBlank(entity.getHttpUrl()) || StringUtils.isBlank(entity.getType())) {
            log.error("url or type of forest request is blank");
            return true;
        }

        // forest中注解形式的参数不可为空, 否则NPE
        if (null == entity.getHeaderMap()) {
            entity.setHeaderMap(new HashMap<>());
        }
        if (null == entity.getRequestParamMap()) {
            entity.setRequestParamMap(new HashMap<>());
        }
        return false;
    }

}
