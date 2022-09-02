package com.guyongzhe.shixun.service.impl;

import com.guyongzhe.shixun.pojo.InspectLog;
import com.guyongzhe.shixun.mapper.InspectLogMapper;
import com.guyongzhe.shixun.service.InspectLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lilac
 * @since 2022-08-23
 */
@Service
public class InspectLogServiceImpl extends ServiceImpl<InspectLogMapper, InspectLog> implements InspectLogService {

    @Override
    public boolean createLog(Integer actualSteps, Integer expSteps, Integer employeeId, boolean ifSuccess){
        InspectLog log = new InspectLog();
        log.setActualFinishSteps(actualSteps);
        log.setExpFinishSteps(expSteps);
        log.setEmployeeId(employeeId);
        log.setIfSuccess(ifSuccess?1:0);
        return save(log);
    }
}
