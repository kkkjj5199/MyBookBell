package com.project.bookbell.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/gotoSign")
    public String loginP(){
        return "login";
    }
}
