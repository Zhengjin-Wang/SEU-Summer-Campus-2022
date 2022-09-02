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

// 开始任务要1.设置对应路线的所有巡检点为未巡检,并2.修改proceeding的状态为1，3.修改人员状态为1;
public class ExpStartTimerTask extends TimerTask {

    private Integer taskItemId;

    private Integer proceedingId;

    private Integer employeeId;

    private ExpStartTimerService timerService;

    public ExpStartTimerTask(Integer taskItemId, Integer proceedingId, Integer employeeId) {
        this.taskItemId = taskItemId;
        this.proceedingId = proceedingId;
        this.employeeId = employeeId;
    }


    @Override
    public void run(){

        timerService = BeanUtils.getBean(ExpStartTimerService.class);
        timerService.run(taskItemId, proceedingId, employeeId);

    }
}
