package com.guyongzhe.shixun.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
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
public class InspectRoute implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 巡检路线ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 巡检路线名称
     */
    private String routeName;

    /**
     * 当前包含的巡检点个数
     */
    private Integer inspectPointNumber;


}
