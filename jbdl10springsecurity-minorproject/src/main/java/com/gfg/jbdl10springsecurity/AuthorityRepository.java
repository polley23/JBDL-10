package com.gfg.jbdl10springsecurity;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthorityRepository extends CrudRepository<Authority,Long> {
    Optional<Authority> findByAuthority(String auth);
}
