package com.gfg.jbdl10springsecurity;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    //Authentication
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(bCryptPasswordEncoder())
                .withUser("admin")
                .password(bCryptPasswordEncoder().encode("password"))
                .roles("ADMIN", "USER")
                .and()
                .withUser("user1")
                .password((bCryptPasswordEncoder().encode("password")))
                .roles("USER");
    }

    //Authorization
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/home")
                .hasAnyRole("ADMIN","USER")
                //.hasAnyRole("ADMIN")
                .and()
                .authorizeRequests()
                .antMatchers("/secret")
                .hasAnyRole("ADMIN")
                .and()
                .formLogin()
        .disable();
    }
}
