package com.china.rescue.business.system.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/hello")
    public String hello() {
        return "product hello";
    }
}