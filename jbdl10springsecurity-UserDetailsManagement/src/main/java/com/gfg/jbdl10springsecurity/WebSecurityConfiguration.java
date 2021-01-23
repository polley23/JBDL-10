package com.gfg.jbdl10springsecurity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    UserManager userManager;

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    //Authentication
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userManager)
                .passwordEncoder(bCryptPasswordEncoder());
        try {
            userManager.loadUserByUsername("admin");
        }catch (Exception e){
            User user = User.builder()
                    .username("admin")
                    .password(bCryptPasswordEncoder().encode("password"))
                    .accountNonExpired(true)
                    .accountNonLocked(true)
                    .credentialsNonExpired(true)
                    .enabled(true)
                    .build();
            Authority authority = Authority.builder()
                    .authority("ADMIN")
                    .users(Arrays.asList(user))
                    .build();
            user.setAuthorityList(Arrays.asList(authority));
            userManager.create(user);
        }


    }

    //Authorization
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/secured/**")
                .authenticated()
                .antMatchers("/signUp")
                .permitAll()
                .and()
                .csrf()
                .disable()
                .formLogin()
                .disable();
    }
}
