package com.gfg.googleAutheticationJBDL10;


import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Resource {

    @GetMapping("/home")
    public String home(Authentication authentication){
        return "Welcome "+authentication.getPrincipal().toString();

    }
}
