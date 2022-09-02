package com.guyongzhe.shixun.service;

import com.guyongzhe.shixun.pojo.InspectEmployee;
import com.guyongzhe.shixun.pojo.InspectPoint;
import com.guyongzhe.shixun.pojo.InspectProceeding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpStartTimerService {

    @Autowired
    InspectTaskItemService taskItemService;

    @Autowired
    InspectPointService pointService;

    @Autowired
    InspectProceedingService proceedingService;

    @Autowired
    InspectEmployeeService employeeService;

    public void run(Integer taskItemId, Integer proceedingId, Integer employeeId){
        try {
            //修改点
            System.out.println(taskItemId);
            System.out.println(taskItemService);
            List<InspectPoint> points = taskItemService.queryPointsByTaskItemId(taskItemId);
            for (InspectPoint point : points) {
                point.setFinish(0);
            }
            pointService.updateBatchById(points);

            //修改进度
            InspectProceeding proceeding = proceedingService.getById(proceedingId);
            proceeding.setStatus(1);
            proceedingService.updateById(proceeding);

            //修改员工
            InspectEmployee employee = employeeService.getById(employeeId);
            employee.setStatus(1);
            employeeService.updateById(employee);
        }
        catch (Exception e){
            e.printStackTrace();
            proceedingService.removeById(proceedingId);
        }
    }

}
