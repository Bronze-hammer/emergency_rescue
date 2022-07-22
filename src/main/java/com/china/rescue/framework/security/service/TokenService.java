package com.china.rescue.framework.security.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.china.rescue.framework.redis.RedisCache;
import com.china.rescue.framework.security.jwt.JWTProperties;
import com.china.rescue.po.LoginUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author: xbronze
 * @CreateTime: 2022-07-21  10:54
 * @Description: TODO
 */
@Component
@Slf4j
public class TokenService {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private JWTProperties jwtProperties;

    /**
     * 生成token
     */
    public String createToken(LoginUser loginUser){

        String cust_token = IdUtil.fastUUID(); // 自定义token
        loginUser.setToken(cust_token);
        refreshToken(loginUser);

        Map<String, Object> claims = new HashMap<>();
        claims.put("login_user_key", loginUser.getToken());
        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256,jwtProperties.getSecret()).compact();
    }

    /**
     * 根据认证的token获取用户信息
     */
    public LoginUser getLoginUserByToken(HttpServletRequest request){
        // 从请求信息中获取token数据
        String authToken = request.getHeader("Authorization");
        if (StringUtils.hasText(authToken)) {
            // 从自定义tokenProvider中解析用户
            authToken = authToken.replace("Bearer ", "");
        }
        if (StrUtil.hasEmpty(authToken))
            return null;
        Claims body = Jwts.parser().setSigningKey(jwtProperties.getSecret()).parseClaimsJws(authToken).getBody();
        String token = body.get("login_user_key") == null ? null : (String) body.get("login_user_key");
        Object redisValue = redisCache.getCacheObject("login_tokens:" + token);
        if (redisValue == null)
            return null;

        return JSONObject.parseObject(redisValue.toString(), LoginUser.class);
    }

    /**
     * 跟新token有效时间
     */
    public void refreshToken(LoginUser loginUser){
        loginUser.setLoginTime(System.currentTimeMillis()); // 登录时间
        loginUser.setExpireTime(loginUser.getLoginTime() + 30 * 60 * 1000); // 过期时间
        String userKey = "login_tokens:" + loginUser.getToken();
        redisCache.setCacheObject(userKey, JSONObject.toJSONString(loginUser), 30, TimeUnit.MINUTES);
    }

}
