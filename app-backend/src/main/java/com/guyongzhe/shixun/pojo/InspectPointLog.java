package com.guyongzhe.shixun.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author lilac
 * @since 2022-08-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("inspect_point_abnormal_log")
public class InspectPointLog implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 巡检点日志ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 上报时间
     */
    @TableField(value="report_time",fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 巡检点描述（详细信息）
     */
    private String description;

    /**
     * 巡检照片存放的路径
     */
    @TableField("picture_path")
    private String photoPath;

    private String status;

    /**
     * 是否存在异常（0表示正常）
     */
    private Integer ifAbnormal;

    /**
     * 外键_巡检点ID
     */
    private Integer inspectPointId;

    /**
     * 外键_巡检人员ID
     */
    private Integer inspectEmployeeId;


}
