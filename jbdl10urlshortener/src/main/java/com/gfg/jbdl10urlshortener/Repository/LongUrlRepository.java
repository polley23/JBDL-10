package com.gfg.jbdl10urlshortener.Repository;

import com.gfg.jbdl10urlshortener.entities.LongURL;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LongUrlRepository extends CrudRepository<LongURL, Long> {
}
