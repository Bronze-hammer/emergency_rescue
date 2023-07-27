package com.china.rescue.controller;

import com.china.rescue.common.ServerResponse;
import com.china.rescue.framework.security.service.LoginService;
import com.china.rescue.po.LoginBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录并返回token
 *
 * @author : xbronze
 * @date : 2022-07-21  10:07
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/getToken")
    public ServerResponse<String> getToken(LoginBody loginBody){
        String token = loginService.getToken(loginBody.getUsername(), loginBody.getPassword());
        return ServerResponse.createBySuccessByData(token);
    }

}
