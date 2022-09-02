package com.guyongzhe.shixun.service;

import com.guyongzhe.shixun.mapper.InspectEmployeeMapper;
import com.guyongzhe.shixun.pojo.InspectEmployee;
import com.guyongzhe.shixun.vo.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 自定义UserDetails查询
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-12-10 10:29
 */
@Transactional
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private InspectEmployeeMapper inspectEmployeeMapper;

    @Override
    public UserDetails loadUserByUsername(String workNo) throws UsernameNotFoundException {


        Map map = new HashMap<String, Object>();
        map.put("user_name", workNo);
        List userList = inspectEmployeeMapper.selectByMap(map);

        if (userList.size() == 0) {
            throw new UsernameNotFoundException("未找到用户信息 : " + workNo);
        }
        InspectEmployee user = (InspectEmployee) userList.get(0);

        return UserPrincipal.create(user, null, new ArrayList<>());
    }
}
