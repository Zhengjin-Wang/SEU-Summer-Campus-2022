package com.guyongzhe.shixun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guyongzhe.shixun.pojo.InspectPoint;
import com.guyongzhe.shixun.pojo.InspectTaskItem;
import com.guyongzhe.shixun.mapper.InspectTaskItemMapper;
import com.guyongzhe.shixun.service.InspectTaskItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lilac
 * @since 2022-08-23
 */
@Service
public class InspectTaskItemServiceImpl extends ServiceImpl<InspectTaskItemMapper, InspectTaskItem> implements InspectTaskItemService {
    @Autowired
    private InspectTaskItemMapper taskItemMapper;

    @Override
    public List<InspectPoint> queryPointsByTaskItemId(Integer id){
        List<InspectPoint> points = taskItemMapper.queryPointsByTaskItemId(id);
        points.sort(Comparator.comparing(InspectPoint::getLongitude));
        return points;
    }
}
