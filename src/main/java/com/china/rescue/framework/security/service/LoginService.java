package com.china.rescue.framework.security.service;

import com.china.rescue.business.system.po.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * @Author: xbronze
 * @CreateTime: 2022-07-21  10:21
 * @Description: TODO
 */
@Component
public class LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    /**
     * 生成token
     */
    public String getToken(String username, String password) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        Object principal = authentication.getPrincipal();
        LoginUser loginUser = (LoginUser) principal;
        return tokenService.createToken(loginUser);
    }
}
