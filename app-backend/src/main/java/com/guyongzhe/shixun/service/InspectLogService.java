package com.guyongzhe.shixun.service;

import com.guyongzhe.shixun.pojo.InspectLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lilac
 * @since 2022-08-23
 */
public interface InspectLogService extends IService<InspectLog> {
    public boolean createLog(Integer actualSteps, Integer expSteps, Integer employeeId, boolean ifSuccess);
}
