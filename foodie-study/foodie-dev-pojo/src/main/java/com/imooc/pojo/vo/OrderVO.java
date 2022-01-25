package com.imooc.pojo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderVO {
    private String orderId;

    private MerchantOrdersVO merchantOrdersVO;
}
