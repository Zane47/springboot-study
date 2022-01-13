package com.imooc.pojo.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserBO {
    private String userName;

    private String password;

    private String confirmedPassword;
}
