package com.gfg.majorprojectjbdl10.entity;


import org.springframework.data.repository.CrudRepository;

@org.springframework.stereotype.Repository
public interface Repository extends CrudRepository<Transaction,Long> {
}
