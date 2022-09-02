package com.guyongzhe.shixun.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class InspectProceeding implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Date expStartTime;

    private Date expEndTime;

    private Date actualStartTime;

    private Date actualEndTime;

    private Integer totalLength;

    private Integer finishStep;

    private Integer taskItemId;

    private Integer employeeId;

    /**
     * 0表示未开始（创建proceeding的时间以及exp_start_time之前），
     * 1表示进行中（exp_start_time和巡检人员完成之前），
     * 2表示已完成（巡检人员完成后到exp_end_time）
     */
    private Integer status;


}
