package com.guyongzhe.web_terminal.vo;

import com.guyongzhe.web_terminal.entity.HazardSource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//新加
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HazardSourceVO {

    private Integer db_id;
    private String type;
    private String area;
    private String number;
    private Double lng;
    private Double lat;
    private Boolean isWork;
    private Integer abnormalNum;

    public static HazardSourceVO buildVO(HazardSource entity){
        HazardSourceVO vo = new HazardSourceVO();
        vo.setAbnormalNum(entity.getAbnormalNumber());
        vo.setArea(entity.getArea());
        vo.setType(entity.getType());
        vo.setNumber(entity.getNumber());
        vo.setLat(entity.getLatitude());
        vo.setLng(entity.getLongitude());
        vo.setIsWork(entity.getState() == 1);
        vo.setDb_id(entity.getId());
        return vo;
    }

    public static HazardSource buildEntity(HazardSourceVO vo){
        HazardSource entity = new HazardSource();
        entity.setAbnormalNumber(vo.getAbnormalNum());
        entity.setArea(vo.getArea());
        entity.setType(vo.getType());
        entity.setNumber(vo.getNumber());
        entity.setLatitude(vo.getLat());
        entity.setLongitude(vo.getLng());
        entity.setState(vo.getIsWork()?1:0);
        entity.setId(vo.getDb_id());
        return entity;
    }

}
