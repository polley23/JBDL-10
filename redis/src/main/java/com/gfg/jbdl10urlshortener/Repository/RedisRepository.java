package com.gfg.jbdl10urlshortener.Repository;

import com.gfg.jbdl10urlshortener.entities.UrlRedis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisRepository extends CrudRepository<UrlRedis, String> {
}
