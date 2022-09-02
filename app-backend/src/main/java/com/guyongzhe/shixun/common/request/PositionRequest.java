package com.guyongzhe.shixun.common.request;

import lombok.Data;

@Data
public class PositionRequest {

    /**
     * 员工id
     */
    private Integer employeeId;

    /**
     * 纬度
     */
    private Double lat;

    /**
     * 经度
     */
    private Double lng;
}
