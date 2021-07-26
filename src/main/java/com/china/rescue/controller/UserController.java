package com.china.rescue.controller;

import com.china.rescue.common.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    public ServerResponse<String> login(){
        System.out.println("this is new China!");
        logger.info("logger test!!!");
        return ServerResponse.createBySuccessByMessage("success!!!");
    }
}
