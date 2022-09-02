package com.guyongzhe.shixun.service;

import com.guyongzhe.shixun.pojo.InspectPointLog;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lilac
 * @since 2022-08-22
 */
public interface InspectPointLogService extends IService<InspectPointLog> {

    /**
     *
     * @param employeeId
     * @param pointId
     * @return 是否操作成功
     */
    public boolean anomalyProcess(Integer employeeId, Integer pointId);

    public Integer finishPoint(Integer employeeId, Integer pointId);

    public boolean uploadFile(MultipartFile file, String uploadPath);
}
