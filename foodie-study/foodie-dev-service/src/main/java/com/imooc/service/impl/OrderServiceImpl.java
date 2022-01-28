package com.imooc.service.impl;

import java.util.Date;
import java.util.Optional;

import com.imooc.enums.OrderStatusEnum;
import com.imooc.enums.YesOrNo;
import com.imooc.mapper.OrderItemsMapper;
import com.imooc.mapper.OrderStatusMapper;
import com.imooc.mapper.OrdersMapper;
import com.imooc.pojo.*;
import com.imooc.pojo.bo.SubmitOrderBO;
import com.imooc.pojo.vo.MerchantOrdersVO;
import com.imooc.pojo.vo.OrderVO;
import com.imooc.service.AddressService;
import com.imooc.service.ItemService;
import com.imooc.service.OrderService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private AddressService addressService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private OrderItemsMapper orderItemsMapper;

    @Autowired
    private OrderStatusMapper orderStatusMapper;

    @Autowired
    private Sid sid;


    /**
     * 用于创建订单相关信息
     * <p>
     * 1. 新订单数据保存
     * 2. 循环根据itemSpecIds保存订单商品信息表
     * 2.1 根据规格id，查询规格的具体信息，主要获取价格
     * 2.2 根据商品id，获得商品信息以及商品图片
     * 2.3 循环保存子订单数据到数据库
     * 2.4 在用户提交订单以后，规格表中需要扣除库存
     * 3. 保存订单状态表
     * 4. 构建商户订单，用于传给支付中心
     * 5. 构建自定义订单vo
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public OrderVO createOrder(SubmitOrderBO submitOrderBO) {
        // ------------------------  1. 新订单数据保存 ------------------------
        Orders newOrder = new Orders();

        String orderId = sid.nextShort();

        newOrder.setId(orderId);
        Optional.ofNullable(submitOrderBO.getUserId()).ifPresent(newOrder::setUserId);

        // address信息
        final UserAddress userAddress =
                addressService.querySpecificAddress(submitOrderBO.getUserId(), submitOrderBO.getAddressId());
        Optional.ofNullable(userAddress.getReceiver()).ifPresent(newOrder::setReceiverName);
        Optional.ofNullable(userAddress.getMobile()).ifPresent(newOrder::setReceiverMobile);
        final String receiverAddress = userAddress.getProvince() + " " +
                userAddress.getCity() + " " +
                userAddress.getDistrict() + " " +
                userAddress.getDetail();
        newOrder.setReceiverAddress(receiverAddress);

        // 包邮, 设置邮费为0
        Integer postAmount = 0;

        newOrder.setPostAmount(postAmount);

        // newOrder.setTotalAmount(0);
        // newOrder.setRealPayAmount(0);
        newOrder.setPayMethod(submitOrderBO.getPayMethod());

        Optional.ofNullable(submitOrderBO.getLeftMsg()).ifPresent(newOrder::setLeftMsg);

        // newOrder.setExtand("");
        newOrder.setIsComment(YesOrNo.NO.type);
        newOrder.setIsDelete(YesOrNo.NO.type);
        newOrder.setCreatedTime(new Date());
        newOrder.setUpdatedTime(new Date());


        // ------------------------ 2. 循环根据itemSpecIds保存订单商品信息表 ------------------------

        String itemSpecIds = submitOrderBO.getItemSpecIds();
        String[] itemSpecArray = itemSpecIds.split(",");

        // 商品原价累计
        Integer totalAmount = 0;
        // 优惠后的实际支付价格累计
        Integer actualPayAmout = 0;
        for (String itemSpecId : itemSpecArray) {
            // todo: 整合redis后，商品购买的数量重新从redis的购物车中获取
            int buyCounts = 1;

            //   2.1 根据规格id，查询规格的具体信息，主要获取价格
            ItemsSpec itemsSpec = itemService.queryItemSpecBySpecId(itemSpecId);
            totalAmount += itemsSpec.getPriceNormal() * buyCounts;
            actualPayAmout += itemsSpec.getPriceDiscount() * buyCounts;

            //   2.2 根据商品id，获得商品信息以及商品图片
            Items item = itemService.queryItemById(itemsSpec.getItemId());
            String imageUrl = itemService.queryItemMainImgByItemId(item.getId());

            //   2.3 循环保存子订单数据到数据库
            OrderItems orderItem = new OrderItems();
            orderItem.setId(sid.nextShort());
            orderItem.setOrderId(orderId);
            orderItem.setItemId(item.getId());
            orderItem.setItemImg(imageUrl);
            orderItem.setItemName(item.getItemName());
            orderItem.setItemSpecId(itemSpecId);
            orderItem.setItemSpecName(itemsSpec.getName());
            orderItem.setPrice(itemsSpec.getPriceDiscount());
            orderItem.setBuyCounts(buyCounts);

            orderItemsMapper.insert(orderItem);

            //   2.4 在用户提交订单以后，规格表中需要扣除库存
            itemService.decreaseItemSpecStock(itemSpecId, buyCounts);
        }

        newOrder.setTotalAmount(totalAmount);
        newOrder.setRealPayAmount(actualPayAmout);
        ordersMapper.insert(newOrder);

        // 3. 保存订单状态表
        OrderStatus waitPayOrderStatus = new OrderStatus();
        waitPayOrderStatus.setOrderId(orderId);
        waitPayOrderStatus.setOrderStatus(OrderStatusEnum.WAIT_PAY.type);
        waitPayOrderStatus.setCreatedTime(new Date());
        orderStatusMapper.insert(waitPayOrderStatus);

        // 4. 构建商户订单，用于传给支付中心
        /*MerchantOrdersVO merchantOrdersVO = new MerchantOrdersVO();
        merchantOrdersVO.setMerchantOrderId(orderId);
        merchantOrdersVO.setMerchantUserId(submitOrderBO.getUserId());
        merchantOrdersVO.setAmount(actualPayAmout + postAmount);
        merchantOrdersVO.setPayMethod(submitOrderBO.getPayMethod());*/


        // 5. 构建自定义订单vo
        OrderVO orderVO = new OrderVO();
        orderVO.setOrderId(orderId);
        // orderVO.setMerchantOrdersVO(merchantOrdersVO);

        return orderVO;
    }

    /**
     * 更新订单状态
     */
    @Override
    public void updateOrderStatus(String merchantOrderId, Integer orderStatus) {
        OrderStatus record = new OrderStatus();
        record.setOrderId(merchantOrderId);
        record.setOrderStatus(orderStatus);
        record.setPayTime(new Date());
        orderStatusMapper.updateByPrimaryKeySelective(record);
    }
}
