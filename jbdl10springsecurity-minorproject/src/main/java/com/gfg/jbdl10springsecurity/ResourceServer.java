package com.gfg.jbdl10springsecurity;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class ResourceServer extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
       resources.resourceId("employee");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
       http.authorizeRequests()
               .antMatchers("/secured/**")
               .access("#oauth2.hasAnyScope('read','write')");
    }
}
