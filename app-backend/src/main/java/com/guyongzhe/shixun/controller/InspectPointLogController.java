package com.guyongzhe.shixun.controller;


import com.guyongzhe.shixun.common.ApiResponse;
import com.guyongzhe.shixun.common.request.InspectPointLogRequest;
import com.guyongzhe.shixun.pojo.InspectPointLog;
import com.guyongzhe.shixun.service.InspectPointLogService;
import com.guyongzhe.shixun.service.InspectPointService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lilac
 * @since 2022-08-22
 */
@RestController
@RequestMapping("/pointLog")
public class InspectPointLogController {

    @Autowired
    private InspectPointLogService inspectPointLogService;

    @Autowired
    private InspectPointService inspectPointService;

    @Value("${spring.web.upload-path}")
    private String uploadPath = "/shixunUpLoadFile";//请修改yml文件内的字段

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");

    @PostMapping("/upload")
    @ApiOperation("请求体的文件参数名设置为file\r先上传文件（图片），失败会返回错误信息，成功会返回文件路径，再调用/poingLog/subimit上传所有信息")
    public ApiResponse upload(@RequestParam MultipartFile[] files){
        String dirPath = uploadPath + "/" + UUID.randomUUID().toString();
        boolean success = true;
        for (MultipartFile file : files) {
            success = success && inspectPointLogService.uploadFile(file, dirPath);
            if(!success){
                return ApiResponse.ofError("上传失败");
            }
        }
        return ApiResponse.ofSuccess(dirPath);
    }

    @PostMapping("/submit")
    @ApiOperation("确认提交时，连续调用两个请求，先调用/pointLog/upload，再调用/pointLog/submit；成功时返回已完成巡检点数量")
    public ApiResponse submitPointLog(@RequestBody InspectPointLogRequest request){

        //判断这个巡检点是否被巡检过
        if(inspectPointService.getById(request.getInspectPointId()).getFinish() == 1){
            return ApiResponse.ofError("该巡检点已完成！");
        }

        //设置提交给数据库的信息
        InspectPointLog pointLog = new InspectPointLog();
        pointLog.setInspectPointId(request.getInspectPointId());
        pointLog.setInspectEmployeeId(request.getInspectEmployeeId());
        pointLog.setDescription(request.getDescription());
        pointLog.setIfAbnormal(request.getIfAbnormal());
        pointLog.setPhotoPath(request.getPhotoPath());

        //如果上报的是异常，做相应处理
        if(pointLog.getIfAbnormal() == 1){
            inspectPointLogService.anomalyProcess(pointLog.getInspectEmployeeId(), pointLog.getInspectPointId());
        }

        //完成了一个巡检，记录哪个巡检点被完成，记录在point表里，proceeding已完成巡检点也加1，若是第一个开始的点，改变状态，若是最后一个完成的点，proceeding改变为已完成状态
        Integer finishPoints = inspectPointLogService.finishPoint(pointLog.getInspectEmployeeId(), pointLog.getInspectPointId());

        //数据库提交
        boolean save = inspectPointLogService.save(pointLog);
        if(save){
            return ApiResponse.ofSuccess(finishPoints);
        }
        else{
            return ApiResponse.ofError();
        }
    }

}

