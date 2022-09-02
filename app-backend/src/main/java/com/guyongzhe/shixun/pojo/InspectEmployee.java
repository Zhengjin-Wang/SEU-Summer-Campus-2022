package com.guyongzhe.shixun.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("巡检员工")
public class InspectEmployee implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 巡检员工ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 工号，作为登录ID
     */
    @ApiModelProperty("工号，作为登录ID")
    @TableField("user_name")
    private String workNo;

    /**
     * 巡检员工密码
     */
    private String password;

    private String jobNumber;
    /**
     * 姓名
     */
    private String name;

    private String sex;

    /**
     * 电话
     */
    @TableField("tel")
    private String phone;

    /**
     * 外键_班ID
     */
    @ApiModelProperty("员工所属班组")
    private Integer inspectClassId;

    private String inspectClassName;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("员工登记时间")
    private Date createTime;

    @ApiModelProperty("总巡检次数")
    @TableField("inspect_number")
    private Integer totalTasks;

    @ApiModelProperty("成功完成巡检的次数")
    @TableField("inspect_finish_number")
    private Integer successTasks;

    @ApiModelProperty("上报异常的数量")
    @TableField("exception_found_number")
    private Integer postAnomalyNum;

    @ApiModelProperty("是否处于巡检状态")
    private Integer status = 0;

}
