package com.gfg.jbdl10springsecurity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Long> {
    Optional<Employee> findByEmployeeId(String auth);

}
