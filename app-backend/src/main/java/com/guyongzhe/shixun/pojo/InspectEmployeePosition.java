package com.guyongzhe.shixun.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author lilac
 * @since 2022-08-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class InspectEmployeePosition implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer employeeId;

    private Double lat;

    private Double lng;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;


}
