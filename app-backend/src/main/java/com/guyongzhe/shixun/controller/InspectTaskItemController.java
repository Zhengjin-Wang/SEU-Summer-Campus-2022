package com.guyongzhe.shixun.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guyongzhe.shixun.common.ApiResponse;
import com.guyongzhe.shixun.mapper.InspectTaskItemMapper;
import com.guyongzhe.shixun.pojo.InspectPoint;
import com.guyongzhe.shixun.pojo.InspectTaskItem;
import com.guyongzhe.shixun.service.InspectTaskItemService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lilac
 * @since 2022-08-23
 */
@RestController
@RequestMapping("/task")
//定时任务，每天固定时间查所有taskitem，并创建proceeding实例
public class InspectTaskItemController {

    @Autowired
    private InspectTaskItemService taskItemService;

    @Autowired
    private InspectTaskItemMapper taskItemMapper;

    @ApiOperation("获取用户的所有巡检任务，用户点击任务时，应判断是否在时间范围内")
    @GetMapping("/{employeeId}")
    public ApiResponse queryTasks(@PathVariable Integer employeeId){
        QueryWrapper<InspectTaskItem> wrapper = new QueryWrapper<>();
        wrapper.eq("inspect_employee_id", employeeId).orderByDesc("id");
        List<InspectTaskItem> items = taskItemService.list(wrapper);
        //修改日期
//        for (InspectTaskItem item : items) {
//            Date startTime = item.getStartTime();
//            Date endTime = item.getEndTime();
//            Date expStartTime = new Date();
//            Date expEndTime = new Date();
//
//            expStartTime.setHours(startTime.getHours());
//            expStartTime.setMinutes(startTime.getMinutes());
//            expStartTime.setSeconds(startTime.getSeconds());
//            expEndTime.setHours(endTime.getHours());
//            expEndTime.setMinutes(endTime.getMinutes());
//            expEndTime.setSeconds(endTime.getSeconds());
//            item.setStartTime(expStartTime);
//            item.setEndTime(expEndTime);
//        }
        return ApiResponse.ofSuccess(items);
    }

    @ApiOperation("根据task_item的id获取巡检路线上的所有点")
    @GetMapping("/points/{itemId}")
    public ApiResponse queryPoints(@PathVariable Integer itemId){
        List<InspectPoint> inspectPoints = taskItemService.queryPointsByTaskItemId(itemId);
        return ApiResponse.ofSuccess(inspectPoints);
    }

    @ApiOperation("获取task的名字")
    @GetMapping("/name/{itemId}")
    public ApiResponse queryTaskName(@PathVariable Integer itemId){
        String name = taskItemMapper.queryTaskNameByTaskItemId(itemId);
        return ApiResponse.ofSuccess(name);
    }



}

