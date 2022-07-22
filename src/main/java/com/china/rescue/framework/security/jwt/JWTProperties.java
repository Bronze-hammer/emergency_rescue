package com.china.rescue.framework.security.jwt;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
public class JWTProperties {

    @Value("${jwtproperties.secret}")
    private String secret; // 秘钥

    @Value("${jwtproperties.tokenValidityInMilliseconds}")
    private long tokenValidityInMilliseconds;  // 有效时间

    @Value("${jwtproperties.tokenValidityInMillisecondsForRememberMe}")
    private long tokenValidityInMillisecondsForRememberMe; // 记住我的有效时间

}
