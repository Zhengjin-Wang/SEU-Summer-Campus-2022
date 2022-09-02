package com.guyongzhe.shixun.service;

import com.guyongzhe.shixun.pojo.InspectEmployee;
import com.guyongzhe.shixun.pojo.InspectPoint;
import com.guyongzhe.shixun.pojo.InspectProceeding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpEndTimerService {

    @Autowired
    InspectTaskItemService taskItemService;

    @Autowired
    InspectPointService pointService;

    @Autowired
    InspectProceedingService proceedingService;

    @Autowired
    InspectEmployeeService employeeService;

    @Autowired
    InspectLogService logService;

    public void run(Integer taskItemId, Integer proceedingId, Integer employeeId){
        try {

            InspectProceeding proceeding = proceedingService.getById(proceedingId);

            //修改员工信息
            InspectEmployee employee = employeeService.getById(employeeId);
            employee.setTotalTasks(employee.getTotalTasks() + 1); //总巡检加1
            if(proceeding.getStatus() == 2){ //巡检已完成成功次数加一
                employee.setSuccessTasks(employee.getSuccessTasks() + 1);
            }
            employee.setStatus(0); //修改为未巡检
            employeeService.updateById(employee);

            //创建一条日志
            logService.createLog(proceeding.getFinishStep(), proceeding.getTotalLength(),
                    employeeId, proceeding.getStatus() == 2);

            //所有点设为未巡检
            List<InspectPoint> points = taskItemService.queryPointsByTaskItemId(taskItemId);
            for (InspectPoint point : points) {
                point.setFinish(0);
            }
            pointService.updateBatchById(points);

        }
        finally {
            proceedingService.removeById(proceedingId);
        }
    }

}
