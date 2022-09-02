package com.guyongzhe.shixun.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("登录请求")
public class LoginRequest {

    /**
     * 用户名或邮箱或手机号
     */
    @ApiModelProperty("非空")
    private String userName;

    /**
     * 密码
     */
    @ApiModelProperty("非空")
    private String password;

    /**
     * 记住我
     */
    private Boolean rememberMe = false;

}

