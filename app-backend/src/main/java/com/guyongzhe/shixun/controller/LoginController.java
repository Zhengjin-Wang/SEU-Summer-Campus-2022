package com.guyongzhe.shixun.controller;

import com.guyongzhe.shixun.common.ApiResponse;
import com.guyongzhe.shixun.common.request.LoginRequest;
import com.guyongzhe.shixun.common.Status;
import com.guyongzhe.shixun.utils.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.guyongzhe.shixun.exception.SecurityException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;
    @PostMapping("/login")
    public ApiResponse login(@RequestBody LoginRequest loginRequest) {
        if (StringUtils.isBlank(loginRequest.getUserName()) || StringUtils.isBlank(loginRequest.getPassword())){
            return ApiResponse.ofStatus(Status.IS_BLANK);
        }
        //应该是进行用户名密码的认证，以及获取该用户在数据库中的权限（用UserDetails中authorities封装，本质上是字符串），加入到上下文中
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtil.createJWT(authentication, loginRequest.getRememberMe());
        HashMap<Object, Object> map = new HashMap<>();
        map.put("token" , jwt);
        return ApiResponse.ofSuccess(jwt);
    }

    @PostMapping("/logout")
    public ApiResponse logout(HttpServletRequest request) {
        try {
            // 设置JWT过期
            jwtUtil.invalidateJWT(request);
        } catch (SecurityException e) {
            throw new SecurityException(Status.UNAUTHORIZED);
        }
        return ApiResponse.ofStatus(Status.LOGOUT);
    }

//    @GetMapping("/auth")
//    public ApiResponse test(@RequestParam(required = false) String str){
//        System.out.println("abc");
//        return ApiResponse.ofSuccess("asd");
//    }


}
