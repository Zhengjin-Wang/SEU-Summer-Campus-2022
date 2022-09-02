package com.guyongzhe.shixun.config.task;


import com.guyongzhe.shixun.pojo.InspectEmployee;
import com.guyongzhe.shixun.pojo.InspectPoint;
import com.guyongzhe.shixun.pojo.InspectProceeding;
import com.guyongzhe.shixun.service.*;
import com.guyongzhe.shixun.utils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.TimerTask;

// 结束时检查数据库里对应状态是否为已完成
// 1.给员工巡检次数+1，2.员工是否完成巡检（未完成则未完成记录+1)，3.修改人员状态为0， 4.加一条日志，5.修改对应路线所有点为未巡检，6.再删除这条proceeding
public class ExpEndTimerTask extends TimerTask {

    private Integer taskItemId;

    private Integer proceedingId;

    private Integer employeeId;

    private ExpEndTimerService timerService;

    public ExpEndTimerTask(Integer taskItemId, Integer proceedingId, Integer employeeId) {
        this.taskItemId = taskItemId;
        this.proceedingId = proceedingId;
        this.employeeId = employeeId;
    }


    @Override
    public void run() {

        timerService = BeanUtils.getBean(ExpEndTimerService.class);
        timerService.run(taskItemId, proceedingId, employeeId);

    }
}
