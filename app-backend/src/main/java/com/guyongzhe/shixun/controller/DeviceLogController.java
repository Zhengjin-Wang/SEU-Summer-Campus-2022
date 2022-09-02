package com.guyongzhe.shixun.controller;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import com.guyongzhe.shixun.common.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/device")
public class DeviceLogController {

    @Autowired
    ElasticsearchClient elasticsearchClient;

    @PostMapping
    public ApiResponse getLog(){
        //elasticsearchClient.
        return ApiResponse.ofError();
    }
}
