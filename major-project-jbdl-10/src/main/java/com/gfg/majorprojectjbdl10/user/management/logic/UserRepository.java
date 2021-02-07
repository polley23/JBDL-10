package com.gfg.majorprojectjbdl10.user.management.logic;


import com.gfg.majorprojectjbdl10.entity.Transaction;
import com.gfg.majorprojectjbdl10.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@org.springframework.stereotype.Repository
public interface UserRepository extends CrudRepository<UserEntity,Long> {
    Optional<UserEntity> findByUsername(String username);
}
