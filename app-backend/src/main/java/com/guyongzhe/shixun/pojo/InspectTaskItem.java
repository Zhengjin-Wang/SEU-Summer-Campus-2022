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
public class InspectTaskItem implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 巡检任务子项ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String inspectEmployeeNumber;

    private String inspectEmployeeName;

    private String inspectClassName;

    private String inspectRouteName;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 对应的巡检任务ID
     */
    private Integer inspectTaskId;

    /**
     * 负责该任务的员工的ID
     */
    private Integer inspectEmployeeId;

    /**
     * 完成状态（默认没有完成，为0）
     */
    private Integer finish;


}
