package com.gfg.jbdl10springsecurity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Resources {

    @GetMapping("/home")
    public String home(){
        return "Hello you are in home";
    }

    @GetMapping("/secret")
    public String secret(){
        return "Hello you are in the secret page";
    }

}
