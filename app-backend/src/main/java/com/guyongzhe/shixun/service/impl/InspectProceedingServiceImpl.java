package com.guyongzhe.shixun.service.impl;

import com.guyongzhe.shixun.pojo.InspectProceeding;
import com.guyongzhe.shixun.mapper.InspectProceedingMapper;
import com.guyongzhe.shixun.service.InspectProceedingService;
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

//对每个proceeding，创建两个定时任务，
// 开始任务要1.设置对应路线的所有巡检点为未巡检,并2.修改proceeding的状态为1，3.修改人员状态为1;
// 结束时检查数据库里对应状态是否为已完成，1.给员工巡检次数+1，2.员工是否完成巡检（未完成则未完成记录+1)，3.并加一条日志，4.修改人员状态为0，5.再删除这条proceeding
@Service
public class InspectProceedingServiceImpl extends ServiceImpl<InspectProceedingMapper, InspectProceeding> implements InspectProceedingService {

}
