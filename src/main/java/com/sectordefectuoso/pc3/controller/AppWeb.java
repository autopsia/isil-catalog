package com.sectordefectuoso.pc3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppWeb {

    @GetMapping(value = {"/", "/login"})
    private String index(){
        return "index";
    }

    @GetMapping("/home")
    private String menu(){
        return "home";
    }


}
