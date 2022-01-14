package com.imooc.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel(value = "user business object",
        description = "From client, the data passed in by the user is formed in this entity")
public class UserBO {
    @ApiModelProperty(value = "user name", name = "userName",
            example = "imooc", required = true)
    private String userName;

    @ApiModelProperty(value = "password", name = "password",
            example = "imooc", required = true)
    private String password;

    @ApiModelProperty(value = "confirmed password", name = "confirmedPassword",
            example = "123456", required = false)
    private String confirmedPassword;
}
