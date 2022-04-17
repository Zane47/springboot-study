package com.imooc.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@ToString
@XmlRootElement(name = "userBO")
public class UserBOXml {
    @XmlElement(name = "userName")
    private String userName;

    @XmlElement(name = "password")
    private String password;

    @XmlElement(name = "confirmedPassword")
    private String confirmedPassword;
}
