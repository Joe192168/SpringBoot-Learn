package com.joe.docker.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class webController {

    @GetMapping("/index")
    public String test(){
        return "/index.html";
    }

}
