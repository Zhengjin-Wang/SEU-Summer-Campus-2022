package com.guyongzhe.shixun.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
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
public class InspectPoint implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 巡检点ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 巡检点类型
     */
    private String type;

    /**
     * 编号
     */
    private String number;

    /**
     * 区域
     */
    private String area;

    /**
     * 经度
     */
    private Double longitude;

    /**
     * 纬度
     */
    private Double latitude;

    /**
     * 异常次数
     */
    private Integer abnormalNumber;

    /**
     * 是否完成检查（默认没有完成检查，为0）
     */
    private Integer finish;

    /**
     * 当前是否正常工作（0为正常）
     */
    private Integer state;

    /**
     * 外键_巡检路线ID
     */
    private Integer inspectRouteId;

    private Integer inspectEmployeeId;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    private String ercode;

}
