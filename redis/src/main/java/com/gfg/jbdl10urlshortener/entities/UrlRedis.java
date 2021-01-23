package com.gfg.jbdl10urlshortener.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@RedisHash("urlRedis")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UrlRedis {
    @Id
    private String id;
    private String longurl;
}
