package com.gfg.majorprojectjbdl10.resources;

import com.gfg.majorprojectjbdl10.entity.UserEntity;
import com.gfg.majorprojectjbdl10.models.SignUpRequest;
import com.gfg.majorprojectjbdl10.models.User;
import com.gfg.majorprojectjbdl10.user.management.logic.UserManager;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserResource {
    @Autowired
    UserManager userManager;

    @PostMapping("/user/signUp")
    public void signUp(@RequestBody SignUpRequest signupRequest){
        userManager.create(signupRequest);
    }

    @GetMapping("/user/{user_id}")
    public UserEntity get(@PathVariable("user_id") String  userId) throws NotFoundException {
        return  userManager.get(userId);
    }
}
