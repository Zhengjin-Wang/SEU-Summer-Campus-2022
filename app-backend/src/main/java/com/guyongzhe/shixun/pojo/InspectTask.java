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
 * @since 2022-08-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class InspectTask implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 巡检任务ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String inspectClassName;

    private String inspectRouteName;

    /**
     * 是否完成（默认没有完成，为0）
     */
    private Integer finish;

    /**
     * 外键_巡检班ID
     */
    private Integer inspectClassId;

    /**
     * 外键_巡检路线ID
     */
    private Integer inspectRouteId;


}
