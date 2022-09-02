package com.guyongzhe.shixun.common.request;

import lombok.Data;

@Data
public class PasswordRequest {
    private String oldPassword;
    private String newPassword;
}
