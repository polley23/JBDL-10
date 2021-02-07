package com.gfg.majorprojectjbdl10.user.management.logic;

import com.gfg.majorprojectjbdl10.entity.UserEntity;
import com.gfg.majorprojectjbdl10.models.SignUpRequest;
import com.gfg.majorprojectjbdl10.models.User;
import javassist.NotFoundException;

public interface UserManager {
    String create(SignUpRequest signUpRequest);
    UserEntity get(String userId) throws NotFoundException;
}
