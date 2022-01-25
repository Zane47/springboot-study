package com.imooc.controller;

import com.imooc.enums.PayMethod;
import com.imooc.pojo.bo.SubmitOrderBO;
import com.imooc.pojo.vo.MerchantOrdersVO;
import com.imooc.pojo.vo.OrderVO;
import com.imooc.service.OrderService;
import com.imooc.utils.CookieUtils;
import com.imooc.utils.JsonResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("orders")
@RestController
public class OrdersController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(OrdersController.class);


    @Autowired
    private OrderService orderService;

    // 这里需要手动注入Bean -> config中
    @Autowired
    private RestTemplate restTemplate;


    /**
     * 创建订单
     * <p>
     * 1. 创建订单
     * 2. 创建订单以后，移除购物车中已结算(已提交)的商品
     * 3. 向支付中心发送当前订单，用于保存支付中心的订单数据
     */
    @ApiOperation(value = "user submit order", notes = "用户下单", httpMethod = "POST")
    @PostMapping("/create")
    public JsonResult createOrder(
            @ApiParam
            @RequestBody SubmitOrderBO submitOrderBO,
            HttpServletRequest request, HttpServletResponse response) {
        logger.info(submitOrderBO.toString());

        // ------------------------ check ------------------------
        if (!submitOrderBO.getPayMethod().equals(PayMethod.WECHATPAY.type) &&
                !submitOrderBO.getPayMethod().equals(PayMethod.ALIPAY.type)) {
            return JsonResult.errorMsg("wrong pay method");
        }

        // 1. 创建订单
        // 2. 创建订单以后，移除购物车中已结算(已提交)的商品
        // 3. 向支付中心发送当前订单，用于保存支付中心的订单数据

        // ------------------------ 1. 创建订单 ------------------------
        OrderVO orderVO = orderService.createOrder(submitOrderBO);
        String orderId = orderVO.getOrderId();

        // ------------------------ 2. 创建订单以后，移除购物车中已结算(已提交)的商品 ------------------------
        /**
         * 1001
         * 2002 -> 用户购买
         * 3003 -> 用户购买
         * 4004
         */
        // todo: 整合redis之后，完善购物车中的已结算商品清除，并且同步到前端的cookie
       CookieUtils.setCookie(request, response, FOODIE_SHOPCART, "", true);


        // ------------------------ 3. 向支付中心发送当前订单，用于保存支付中心的订单数据 ------------------------

        /*MerchantOrdersVO merchantOrdersVO = orderVO.getMerchantOrdersVO();

        merchantOrdersVO.setReturnUrl(payReturnUrl);

        // 为了方便测试购买，所以所有的支付金额都统一改为1分钱
        merchantOrdersVO.setAmount(1);


        // 支付中心
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("imoocUserId", "imooc");
        headers.add("password", "imooc");

        HttpEntity<MerchantOrdersVO> entity = new HttpEntity<>(merchantOrdersVO, headers);
        ResponseEntity<JsonResult> responseEntity =
                restTemplate.postForEntity(paymentUrl, entity, JsonResult.class);
        JsonResult body = responseEntity.getBody();
        if (body.getStatus() != 200) {
            logger.error("发送错误：{}", body.getMsg());
            return JsonResult.errorMsg("支付中心订单创建失败，请联系管理员！");
        }
*/

        return JsonResult.ok(orderId);
    }


}
