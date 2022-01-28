package com.imooc.service;

import com.imooc.pojo.bo.SubmitOrderBO;
import com.imooc.pojo.vo.OrderVO;

public interface OrderService {

    /**
     * 用于创建订单相关信息
     */
    public OrderVO createOrder(SubmitOrderBO submitOrderBO);


    /**
     * 更新订单状态
     */
    public void updateOrderStatus(String merchantOrderId, Integer orderStatus);



}
