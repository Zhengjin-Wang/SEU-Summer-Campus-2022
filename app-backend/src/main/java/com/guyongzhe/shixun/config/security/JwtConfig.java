package com.guyongzhe.shixun.config.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>
 * JWT 配置
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-12-07 13:42
 */
@ConfigurationProperties(prefix = "jwt.config")
@Data
public class JwtConfig {
    /**
     * jwt 加密 key，默认值：wzj.
     */
    private String key = "wzj";

    /**
     * jwt 过期时间，默认值：600000 {@code 10 分钟}.
     */
    private Long ttl = 600000 * 6 * 24L; //修改为1小时

    /**
     * 开启 记住我 之后 jwt 过期时间，默认值 604800000 {@code 7 天}
     */
    private Long remember = 604800000L;
}
