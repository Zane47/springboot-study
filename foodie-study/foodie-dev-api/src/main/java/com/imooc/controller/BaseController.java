package com.imooc.controller;

import org.springframework.stereotype.Controller;

@Controller
public class BaseController {
    public static final Integer COMMON_PAGE_SIZE = 10;
    public static final Integer PAGE_SIZE = 20;


    // ------------------------ order相关 ------------------------
    public static final String FOODIE_SHOPCART = "shopcart";


    // 支付中心的调用地址
    String paymentUrl = "http://payment.t.mukewang.com/foodie-payment/payment/createMerchantOrder";		// produce

    // 微信支付成功 -> 支付中心 -> 天天吃货平台
    //                       |-> 回调通知的url
    // String payReturnUrl = "http://api.z.mukewang.com/foodie-dev-api/orders/notifyMerchantOrderPaid";
    String payReturnUrl = "localhost:8088/orders/notifyMerchantOrderPaid?merchantOrderId=2201266YB4XX89D4";


}
