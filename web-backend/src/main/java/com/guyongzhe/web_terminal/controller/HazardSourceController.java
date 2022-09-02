package com.guyongzhe.web_terminal.controller;

import com.guyongzhe.web_terminal.common.Result;
import com.guyongzhe.web_terminal.entity.HazardSource;
import com.guyongzhe.web_terminal.entity.InspectEmployee;
import com.guyongzhe.web_terminal.service.HazardSourceService;
import com.guyongzhe.web_terminal.vo.HazardSourceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//修改过
@RestController
@RequestMapping(value = "/hazard")
@CrossOrigin
public class HazardSourceController {
    @Autowired
    HazardSourceService hazardSourceService;

    @PostMapping("/add")
    public Result add(@RequestBody HazardSourceVO hazardSourceVO){
        return new Result("0","保存成功",
                HazardSourceVO.buildVO(
                        (HazardSource) hazardSourceService.add(HazardSourceVO.buildEntity(hazardSourceVO))
                                .getData()));
    }//新增信息

    @DeleteMapping("/delete")
    public Result delete(Integer id){
        return hazardSourceService.delete(id);
    }//删除信息

    @PutMapping("/update")
    public Result update(@RequestBody  HazardSourceVO hazardSourceVO){
        return hazardSourceService.update(HazardSourceVO.buildEntity(hazardSourceVO));
    }//更新信息


    @GetMapping("/find")
    public List<HazardSourceVO> find(String type,String area,String number){
        List<HazardSource> list =  hazardSourceService.find(type,area,number);
        List<HazardSourceVO> vos = new ArrayList<>();
        for (HazardSource hazardSource : list) {
            vos.add(HazardSourceVO.buildVO(hazardSource));
        }
        return vos;
    }//查询员工信息

    @GetMapping("/findAll")
    public List<HazardSourceVO> findAll(){//根据班名返回员工信息
        List<HazardSource> list =  hazardSourceService.findAll();
        List<HazardSourceVO> vos = new ArrayList<>();
        for (HazardSource hazardSource : list) {
            vos.add(HazardSourceVO.buildVO(hazardSource));
        }
        return vos;
    }
}
