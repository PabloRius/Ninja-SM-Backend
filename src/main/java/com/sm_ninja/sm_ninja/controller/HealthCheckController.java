package com.sm_ninja.sm_ninja.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HealthCheckController {
    
    @GetMapping
    public String getHealthCheck(){
        return "Health Check is OK";
    }
}
