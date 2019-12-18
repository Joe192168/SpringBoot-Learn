package com.joe.makerdocker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MarkerController {

    @GetMapping("/")
    public String index(){
        return "hello docker!";
    }

}
