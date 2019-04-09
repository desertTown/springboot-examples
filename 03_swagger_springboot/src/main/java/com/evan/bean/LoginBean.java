package com.evan.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "用户对象", description = "用户对象model")
@Data
public class LoginBean {

    @ApiModelProperty(value = "用户登录账户", name = "loginName", required = true, example = "admin")
    private String name;//   账号
    @ApiModelProperty(value = "用户登录密码", name = "loginPwd", required = true, example = "123456")
    private String password;//   密码
    @ApiModelProperty(hidden = true)
    private String salt;//   盐值

}
