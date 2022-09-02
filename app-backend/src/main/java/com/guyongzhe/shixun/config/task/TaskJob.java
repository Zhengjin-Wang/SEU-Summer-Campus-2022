package com.guyongzhe.shixun.config.task;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guyongzhe.shixun.pojo.InspectProceeding;
import com.guyongzhe.shixun.pojo.InspectRoute;
import com.guyongzhe.shixun.pojo.InspectTaskItem;
import com.guyongzhe.shixun.service.InspectProceedingService;
import com.guyongzhe.shixun.service.InspectRouteService;
import com.guyongzhe.shixun.service.InspectTaskItemService;
import com.guyongzhe.shixun.service.InspectTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;

/**
 * <p>
 * 定时任务
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-11-22 19:09
 */
@Component
@Slf4j
public class TaskJob {

    @Autowired
    private InspectTaskItemService taskItemService;

    @Autowired
    private InspectTaskService taskService;

    @Autowired
    private InspectRouteService routeService;

    @Autowired
    private InspectProceedingService proceedingService;


    /**
     * 每天凌晨0点， 从数据库读取所有task_item，并生成当日的proceeding
     */
    @Scheduled(cron = "0 0 0 * * ?")
    //@PostConstruct
    public void job1() {
        log.info("【定时生成proceeding】开始执行：{}", DateUtil.formatDateTime(new Date()));
        List<InspectTaskItem> items = taskItemService.list();
        for (InspectTaskItem item : items) {
            //创建proceeding实例，设定时间
            InspectProceeding proceeding = new InspectProceeding();
            Date startTime = item.getStartTime();
            Date endTime = item.getEndTime();
            Date expStartTime = new Date();
            Date expEndTime = new Date();

            //当前时间
            Time time1 = new Time(expStartTime.getHours(), expStartTime.getMinutes(), expStartTime.getSeconds());
            //预计开始时间
            Time time2 = new Time(startTime.getHours(), startTime.getMinutes(), startTime.getSeconds());
            //如果任务预计开始时间早于当前时间，跳过
            if(time2.getTime() < time1.getTime()) {
                continue;
            }

            expStartTime.setHours(startTime.getHours());
            expStartTime.setMinutes(startTime.getMinutes());
            expStartTime.setSeconds(startTime.getSeconds());
            expEndTime.setHours(endTime.getHours());
            expEndTime.setMinutes(endTime.getMinutes());
            expEndTime.setSeconds(endTime.getSeconds());

            proceeding.setExpStartTime(expStartTime);
            proceeding.setExpEndTime(expEndTime);

            //通过task_id查route_id再查长度
            Integer taskId = item.getInspectTaskId();
            InspectRoute route = routeService.getById(taskService.getById(taskId).getInspectRouteId());

            //设置杂项
            proceeding.setTotalLength(route.getInspectPointNumber());
            proceeding.setStatus(0);
            proceeding.setFinishStep(0);
            proceeding.setTaskItemId(item.getId());
            proceeding.setEmployeeId(item.getInspectEmployeeId());

            //System.out.println(proceeding);
            //保存到数据库
            proceedingService.save(proceeding);
            Integer proceedingId = proceedingService
                    .list(new QueryWrapper<InspectProceeding>().orderByDesc("id")).get(0).getId();

            //延后开始时间，提早结束时间，防止相邻两个时间段重合（可以在创建task_item时就校验并设置）
            expStartTime.setSeconds(expStartTime.getSeconds());
            expEndTime.setSeconds(expEndTime.getSeconds());

            //创建两个定时任务
            try {
                Timer timer = new Timer();
                //exp_start_time时的行为
                // 开始任务要1.设置对应路线的所有巡检点为未巡检,并2.修改proceeding的状态为1，3.修改人员状态为1;
                timer.schedule(new ExpStartTimerTask(item.getId(), proceedingId, item.getInspectEmployeeId()), expStartTime);
                //exp_end_time时的行为
                // 结束时检查数据库里对应状态是否为已完成
                // 1.给员工巡检次数+1，2.员工是否完成巡检（未完成则未完成记录+1)，3.并加一条日志，4.修改人员状态为0，5.再删除这条proceeding，6.修改对应路线所有点为未巡检
                timer.schedule(new ExpEndTimerTask(item.getId(), proceedingId, item.getInspectEmployeeId()), expEndTime);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}