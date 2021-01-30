package com.gfg.jbdl10springsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Arrays;


@Service
public class UserManager implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        return userRepository.findByUsername(s).orElseThrow(()->new UsernameNotFoundException("username is not found for : "+s));
    }
    public void create(User user){
        userRepository.save(user);
    }
    public void create(SignUpRequest signUpRequest){
        try {
            loadUserByUsername(signUpRequest.getUsername());
        }catch (UsernameNotFoundException exception){
            User user = User.builder()
                    .username(signUpRequest.getUsername())
                    .password(bCryptPasswordEncoder.encode(signUpRequest.getPassword()))
                    .accountNonExpired(true)
                    .accountNonLocked(true)
                    .credentialsNonExpired(true)
                    .enabled(true)
                    .build();
            Authority authority;
            try {
                authority = authorityRepository.findByAuthority("USER").orElseThrow(() -> new Exception());
                if(authority.getUsers()==null){
                    authority.setUsers(Arrays.asList(user));
                }
            }catch (Exception e){
                authority = Authority.builder()
                        .authority("USER")
                        .users(Arrays.asList(user))
                        .build();
            }
            user.setAuthorityList(Arrays.asList(authority));
            userRepository.save(user);
        }
    }
}
