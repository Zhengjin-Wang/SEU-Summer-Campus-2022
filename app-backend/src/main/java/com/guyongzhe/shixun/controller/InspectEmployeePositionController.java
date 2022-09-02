package com.guyongzhe.shixun.controller;


import com.guyongzhe.shixun.common.ApiResponse;
import com.guyongzhe.shixun.common.request.PositionRequest;
import com.guyongzhe.shixun.pojo.InspectEmployeePosition;
import com.guyongzhe.shixun.service.ElasticsearchService;
import com.guyongzhe.shixun.service.InspectEmployeePositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lilac
 * @since 2022-08-23
 */
@RestController
@RequestMapping("/position")
public class InspectEmployeePositionController {

    //@Autowired
    //private InspectEmployeePositionService positionService;

    @Autowired
    private ElasticsearchService elasticsearchService;

    @PostMapping
    public ApiResponse getPosition(@RequestBody PositionRequest request){

//        InspectEmployeePosition pos = new InspectEmployeePosition();
//        pos.setEmployeeId(request.getEmployeeId());
//        pos.setLat(request.getLat());
//        pos.setLng(request.getLng());
//        System.out.println(pos);
//        EsPosition position = new EsPosition();
//        position.setEmployee_id(request.getEmployeeId());
//        position.setCreate_time(new Date());
//        position.setPosition(new GeoPoint(request.getLat(), request.getLng()));
//        try{
//            elasticsearchService.save(position);
//            return ApiResponse.ofSuccess();
//        }
//        catch (Exception e){
//            return ApiResponse.ofError(e.getMessage());
//        }
        return ApiResponse.ofSuccess();
    }
}

