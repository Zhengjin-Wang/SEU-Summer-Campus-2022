package com.guyongzhe.shixun.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("在巡检点上传的信息")
public class InspectPointLogRequest implements Serializable {
    private static final long serialVersionUID=1L;

    /**
     * 巡检点描述（详细信息）
     */
    @ApiModelProperty("备注信息")
    private String description;

    /**
     * 巡检照片存放的路径
     */
    @ApiModelProperty("/pointLog/upload接口返回的文件路径")
    private String photoPath;

    /**
     * 是否存在异常（0表示正常）
     */
    @ApiModelProperty("0是无异常，1是有异常")
    private Integer ifAbnormal;

    /**
     * 外键_巡检点ID
     */
    @ApiModelProperty("巡检点ID")
    private Integer inspectPointId;

    /**
     * 外键_巡检人员ID
     */
    @ApiModelProperty("巡检人员ID")
    private Integer inspectEmployeeId;


}
