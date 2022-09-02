package com.guyongzhe.shixun.service.impl;

import ch.qos.logback.core.status.Status;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guyongzhe.shixun.common.ApiResponse;
import com.guyongzhe.shixun.exception.BaseException;
import com.guyongzhe.shixun.pojo.InspectEmployee;
import com.guyongzhe.shixun.pojo.InspectPoint;
import com.guyongzhe.shixun.pojo.InspectPointLog;
import com.guyongzhe.shixun.mapper.InspectPointLogMapper;
import com.guyongzhe.shixun.pojo.InspectProceeding;
import com.guyongzhe.shixun.service.InspectEmployeeService;
import com.guyongzhe.shixun.service.InspectPointLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guyongzhe.shixun.service.InspectPointService;
import com.guyongzhe.shixun.service.InspectProceedingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lilac
 * @since 2022-08-22
 */
@Service
public class InspectPointLogServiceImpl extends ServiceImpl<InspectPointLogMapper, InspectPointLog> implements InspectPointLogService {

    @Autowired
    InspectProceedingService proceedingService;

    @Autowired
    InspectEmployeeService employeeService;

    @Autowired
    InspectPointService pointService;

    @Override
    public boolean anomalyProcess(Integer employeeId, Integer pointId) {
        InspectEmployee employee = employeeService.getById(employeeId);
        employee.setPostAnomalyNum(employee.getPostAnomalyNum() + 1);
        boolean s1 = employeeService.updateById(employee);
        InspectPoint point = pointService.getById(pointId);
        point.setAbnormalNumber(point.getAbnormalNumber() + 1);
        boolean s2 = pointService.updateById(point);
        return s1 && s2;
    }

    /**
     * 根据employeeId查proceeding，让finish_step加1，同时更新pointId的状态
     * 如果是第一个点，改变proceeding状态，设置actual_start_time，最后一个点，也改变proceeding状态，设置actual_end_time
     * @param employeeId
     * @param pointId
     * @return 已完成点的数量
     */
    @Override
    public Integer finishPoint(Integer employeeId, Integer pointId) {

        //修改proceeding的相关信息
        QueryWrapper<InspectProceeding> wrapper = new QueryWrapper<>();
        wrapper.eq("employee_id", employeeId).eq("status", 1);

        InspectProceeding proceeding = null;
        try {
            proceeding = proceedingService.list(wrapper).get(0);
        }catch (Exception e){
            throw new BaseException(4001, "未找到巡检员工对应的proceeding");
        }

        Integer step = proceeding.getFinishStep();
        if(step == 0){
            proceeding.setActualStartTime(new Date());
            //proceeding.setStatus(1);
        }
        proceeding.setFinishStep(++step);
        //若已经完成，设置终止时间和proceeding的状态
        if (step.equals(proceeding.getTotalLength())){
            proceeding.setActualEndTime(new Date());
            proceeding.setStatus(2);
        }

        //修改point的相关信息
        InspectPoint point = pointService.getById(pointId);
        point.setFinish(1);
        pointService.updateById(point);

        return step;
    }

    @Override
    public boolean uploadFile(MultipartFile file, String uploadPath) {
        if(!file.isEmpty()){
            // 如果目录不存在则创建
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String OriginalFilename = file.getOriginalFilename();//获取原文件名
            String suffixName = OriginalFilename.substring(OriginalFilename.lastIndexOf("."));//获取文件后缀名
            //重新随机生成名字
            String filename = UUID.randomUUID().toString() +suffixName;
            String absolutePath = uploadPath+"/"+filename;
            File localFile = new File(absolutePath);
            try {
                file.transferTo(localFile); //把上传的文件保存至本地
                /**
                 * 这里应该把filename保存到数据库,供前端访问时使用
                 */
                return true;//上传成功，返回保存的文件地址
            }catch (IOException e){
                e.printStackTrace();
                return false;
            }
        }else{
            return false;
        }
    }
}
