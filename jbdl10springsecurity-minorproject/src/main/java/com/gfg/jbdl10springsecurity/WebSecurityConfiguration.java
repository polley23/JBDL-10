package com.gfg.jbdl10springsecurity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
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
    EmployeeManager employeeManager;

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    //Authentication
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(employeeManager)
                .passwordEncoder(bCryptPasswordEncoder());
        try {
            employeeManager.getByEmployeeId("admin");
        }catch (Exception e){
            Employee user = Employee.builder()
                    .firstName("admin")
                    .lastName("admin")
                    .username("admin")
                    .employeeId("admin")
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
            employeeManager.create(user);
        }

        try {
            employeeManager.getByEmployeeId("JohnDoe");
        }catch (Exception e){
            Employee user = Employee.builder()
                    .firstName("John")
                    .lastName("Doe")
                    .username("JohnDoe")
                    .employeeId("JohnDoe")
                    .password(bCryptPasswordEncoder().encode("Doe"))
                    .accountNonExpired(true)
                    .accountNonLocked(true)
                    .credentialsNonExpired(true)
                    .enabled(true)
                    .build();
            Authority authority = Authority.builder()
                    .authority("HR")
                    .users(Arrays.asList(user))
                    .build();
            user.setAuthorityList(Arrays.asList(authority));
            employeeManager.create(user);
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
                .antMatchers("/employee/**")
                .authenticated()
                .antMatchers("/signUp")
                .permitAll()
                .and()
                .csrf()
                .disable()
                .formLogin()
                .disable();
    }
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
