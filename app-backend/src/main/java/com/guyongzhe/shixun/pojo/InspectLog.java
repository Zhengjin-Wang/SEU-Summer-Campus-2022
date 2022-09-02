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
 * @since 2022-08-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class InspectLog implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    private Integer actualFinishSteps;

    private Integer expFinishSteps;

    private Integer employeeId;

    /**
     * 0表示失败，1表示成功
     */
    private Integer ifSuccess;


}
