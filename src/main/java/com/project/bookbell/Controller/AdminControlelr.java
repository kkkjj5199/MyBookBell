package com.project.bookbell.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminControlelr {

    @GetMapping("/admin")
    public String adminPage(){
        return "admin";
    }
}
