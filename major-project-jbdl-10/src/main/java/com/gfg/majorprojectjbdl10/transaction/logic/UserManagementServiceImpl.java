package com.gfg.majorprojectjbdl10.transaction.logic;

import com.gfg.majorprojectjbdl10.entity.UserEntity;
import com.gfg.majorprojectjbdl10.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserManagementServiceImpl implements UserManagementService{
    @Autowired
    RestTemplate restTemplate;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

            UserEntity userEntity = restTemplate.getForEntity("http://localhost:8080/user/" + username, UserEntity.class).getBody();
            return User.builder()
                    .accountNonExpired(userEntity.getAccountNonExpired())
                    .credentialsNonExpired(userEntity.getCredentialsNonExpired())
                    .enabled(userEntity.getEnabled())
                    .accountNonLocked(userEntity.getAccountNonLocked())
                    .username(userEntity.getUsername())
                    .password(userEntity.getPassword())
                    .grantedAuthority(new User.Authority(userEntity.getAuthority()))
                    .build();
    }
}
