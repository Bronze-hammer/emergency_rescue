package com.china.rescue.controller;

import com.china.rescue.common.ServerResponse;
import com.china.rescue.po.User;
import com.china.rescue.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;




    @GetMapping(value = "/{id}")
    public ServerResponse<User> loginWithId(@PathVariable("id") Long id){
        return ServerResponse.createBySuccessByData(userService.findUserById(id));
    }


}
