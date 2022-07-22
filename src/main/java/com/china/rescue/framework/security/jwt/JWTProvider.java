package com.china.rescue.framework.security.jwt;

import com.china.rescue.mapper.UserMapper;
import com.china.rescue.po.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class JWTProvider {

    private Key key;  // 私钥
    private long tokenValidityInMilliseconds;  // 有效时间
    private long tokenValidityInMillisecondsForRememberMe; // 记住我的有效时间

    @Autowired
    private JWTProperties jwtProperties;

    @Autowired
    private UserMapper userMapper;

    @PostConstruct
    public void init(){
        byte[] keyBytes = new byte[0];
        String secret = jwtProperties.getSecret();
        if (StringUtils.hasText(secret)){
            log.warn("Warning: the JWT key used is not Base64-encoded. " +
                    "We recommend using the `jhipster.security.authentication.jwt.base64-secret` key for optimum security.");
            keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        }

        this.key = Keys.hmacShaKeyFor(keyBytes); // 使用mac-sha算法的秘钥
        this.tokenValidityInMilliseconds = 1000 * jwtProperties.getTokenValidityInMilliseconds();
        this.tokenValidityInMillisecondsForRememberMe = 1000 * jwtProperties.getTokenValidityInMillisecondsForRememberMe();
    }

    public String createToken(Authentication authentication, boolean rememberMe){
        long now = (new Date()).getTime();
        Date validity;
        if (rememberMe){
            validity = new Date(now + this.tokenValidityInMillisecondsForRememberMe);
        } else {
            validity = new Date(now + this.tokenValidityInMilliseconds);
        }
        User user = userMapper.selectUserByLogin(authentication.getName());
        Map<String, Object> map = new HashMap<>();
        map.put("sub", authentication.getName());
        map.put("user", user);
        return Jwts.builder().setClaims(map)
                .signWith(key, SignatureAlgorithm.HS512) //指定摘要算法
                .setExpiration(validity)  // 设置有效时间
                .compact();
    }

    public Authentication getAuthentication(String token){
        //根据token获取body
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        User user = userMapper.selectUserByLogin(claims.getSubject());
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        return new UsernamePasswordAuthenticationToken(user, token, authorities);

    }



}
