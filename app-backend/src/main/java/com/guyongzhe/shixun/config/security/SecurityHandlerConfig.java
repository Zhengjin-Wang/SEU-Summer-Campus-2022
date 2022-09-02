package com.guyongzhe.shixun.config.security;

import com.guyongzhe.shixun.common.Status;
import com.guyongzhe.shixun.utils.ResponseUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * <p>
 * Security 结果处理配置
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-12-07 17:31
 */
@Configuration
public class SecurityHandlerConfig {

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> ResponseUtil.renderJson(response, Status.ACCESS_DENIED, null);
    }

}
