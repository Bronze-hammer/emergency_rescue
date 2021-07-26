package com.china.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController {

    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    public String login(){
        System.out.println("thi is China!");
        return "hello world China";
    }
}
