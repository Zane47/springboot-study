package com.imooc.enums;

public enum PayMethod {

    WECHATPAY(1, "WeChar"),
    ALIPAY(2, "AliPay");

    public final Integer type;
    public final String value;

    PayMethod(Integer type, String value) {
        this.type = type;
        this.value = value;
    }

}
