package com.guyongzhe.shixun.mapper;

import com.guyongzhe.shixun.pojo.InspectPoint;
import com.guyongzhe.shixun.pojo.InspectTaskItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lilac
 * @since 2022-08-23
 */
public interface InspectTaskItemMapper extends BaseMapper<InspectTaskItem> {
    public List<InspectPoint> queryPointsByTaskItemId(Integer id);

    public String queryTaskNameByTaskItemId(Integer id);
}
