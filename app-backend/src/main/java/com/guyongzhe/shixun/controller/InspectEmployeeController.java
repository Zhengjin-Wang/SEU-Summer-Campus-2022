package com.guyongzhe.shixun.controller;


import com.guyongzhe.shixun.common.ApiResponse;
import com.guyongzhe.shixun.common.request.PasswordRequest;
import com.guyongzhe.shixun.common.Status;
import com.guyongzhe.shixun.mapper.InspectEmployeeMapper;
import com.guyongzhe.shixun.pojo.InspectEmployee;
import com.guyongzhe.shixun.service.InspectEmployeeService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lilac
 * @since 2022-08-22
 */
@RestController
@RequestMapping("/employee")
public class InspectEmployeeController {
    @Autowired
    private InspectEmployeeService inspectEmployeeService;

    @Autowired
    private InspectEmployeeMapper inspectEmployeeMapper;

    @GetMapping("/{workNo}")
    @ApiOperation("登陆成功后，用work_no查询对应的员工信息")
    public ApiResponse queryEmployee(@PathVariable String workNo){
        Map map = new HashMap<String, Object>();
        map.put("user_name", workNo);
        List userList = inspectEmployeeMapper.selectByMap(map);
        if (userList.size() == 0) {
            throw new UsernameNotFoundException("未找到用户信息 : " + workNo);
        }
        InspectEmployee user = (InspectEmployee) userList.get(0);
        return ApiResponse.ofSuccess(user);
    }

    @PostMapping("/password/{id}")
    @ApiOperation("根据用户主键id修改密码，并在requestBody中传入旧密码和新密码")
    public ApiResponse changePassword(@PathVariable Integer id, @RequestBody PasswordRequest passwordRequest){

        if (StringUtils.isBlank(passwordRequest.getOldPassword()) || StringUtils.isBlank(passwordRequest.getOldPassword())){
            return ApiResponse.ofStatus(Status.IS_BLANK);
        }

        InspectEmployee inspectEmployee = inspectEmployeeService.getById(id);
        String newPassword = passwordRequest.getNewPassword();
        String oldPassword = passwordRequest.getOldPassword();
        if(!new BCryptPasswordEncoder().matches(oldPassword, inspectEmployee.getPassword())){
            return ApiResponse.ofStatus(Status.USERNAME_PASSWORD_ERROR);
        }
        inspectEmployee.setPassword(new BCryptPasswordEncoder().encode(newPassword));
        //inspectEmployee.setName("PW: " + newPassword);
        inspectEmployeeService.updateById(inspectEmployee);
        return ApiResponse.ofSuccess();
    }
}

