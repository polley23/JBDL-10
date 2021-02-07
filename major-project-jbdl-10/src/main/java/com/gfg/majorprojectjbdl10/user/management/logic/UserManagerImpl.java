package com.gfg.majorprojectjbdl10.user.management.logic;

import com.gfg.majorprojectjbdl10.entity.UserEntity;
import com.gfg.majorprojectjbdl10.models.SignUpRequest;
import com.gfg.majorprojectjbdl10.models.User;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserManagerImpl implements UserManager{
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    KafkaTemplate kafkaTemplate;
    @Override
    public String create(SignUpRequest signUpRequest) {
        UserEntity user = UserEntity.builder()
                .username(signUpRequest.getUsername())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .email(signUpRequest.getEmail())
                .authority("USER")
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build();
        UserEntity userPersisted = userRepository.save(user);
        kafkaTemplate.send("walletCreation", userPersisted.getUsername());
        return userPersisted.getUsername();
    }

    @Override
    public UserEntity get(String userId) throws NotFoundException {
        UserEntity userEntity =  userRepository.findByUsername(userId)
                .orElseThrow(()->new NotFoundException("user id not found. user id : "+userId));
        return userEntity;
    }
}
