package com.gfg.jbdl10springsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Resources {
    @Autowired
    UserManager userManager;

    @GetMapping("/secured/home")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public String home(){
        return "Hello you are in home";
    }

    @GetMapping("/secured/secret")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String secret(){
        return "Hello you are in the secret page";
    }


    @PostMapping("/signUp")
    public void signUp(@RequestBody SignUpRequest signupRequest ){
       userManager.create(signupRequest);
    }
}
