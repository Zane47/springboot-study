package com.imooc.pojo.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@ToString
@XmlRootElement(name = "message")
public class AddressBOXml2 {
    private String addressId;

    @XmlElement(name = "userId")
    private String userId;

    @XmlElement(name = "receiver")
    private String receiver;

    @XmlElement(name = "mobile")
    private String mobile;

    @XmlElement(name = "province")
    private String province;

    @XmlElement(name = "city")
    private String city;

    @XmlElement(name = "district")
    private String district;

    @XmlElement(name = "detail")
    private String detail;

    @XmlElement(name = "userBO")
    private UserBOXml userBO;
}
