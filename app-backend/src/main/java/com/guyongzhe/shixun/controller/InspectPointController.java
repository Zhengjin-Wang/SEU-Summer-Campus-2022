package com.guyongzhe.shixun.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guyongzhe.shixun.common.ApiResponse;
import com.guyongzhe.shixun.pojo.InspectPoint;
import com.guyongzhe.shixun.service.InspectPointService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/point")
public class InspectPointController {

    @Autowired
    InspectPointService pointService;

    @GetMapping("/{erCode}")
    @ApiOperation("通过二维码扫描的结果获取巡检点，返回一个巡检点实体")
    public ApiResponse getPointByERcode(@PathVariable String erCode){
        try {
            InspectPoint point = pointService.list(new QueryWrapper<InspectPoint>().eq("ercode", erCode)).get(0);
            return ApiResponse.ofSuccess(point);
        }catch (Exception e){
            return ApiResponse.ofError("未找到巡检点！");
        }
    }

}
