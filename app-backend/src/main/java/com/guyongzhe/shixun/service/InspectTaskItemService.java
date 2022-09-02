package com.guyongzhe.shixun.service;

import com.guyongzhe.shixun.pojo.InspectPoint;
import com.guyongzhe.shixun.pojo.InspectTaskItem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lilac
 * @since 2022-08-23
 */
public interface InspectTaskItemService extends IService<InspectTaskItem> {
    public List<InspectPoint> queryPointsByTaskItemId(Integer id);
}
