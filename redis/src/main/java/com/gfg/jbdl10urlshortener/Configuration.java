package com.gfg.jbdl10urlshortener;


import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    RedisTemplate<String,String> redisTemplate(){
        RedisTemplate<String,String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        return redisTemplate;
    }

    @Bean
    RedisConnectionFactory redisConnectionFactory(){
        RedisStandaloneConfiguration redisStandaloneConfiguration
                = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration
                .setHostName("127.0.0.1");
        redisStandaloneConfiguration.setDatabase(5);
        return new JedisConnectionFactory(redisStandaloneConfiguration, JedisClientConfiguration.builder().build());
    }



}
