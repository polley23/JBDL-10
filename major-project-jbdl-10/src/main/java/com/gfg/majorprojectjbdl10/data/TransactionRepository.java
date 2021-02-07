package com.gfg.majorprojectjbdl10.data;


import com.gfg.majorprojectjbdl10.entity.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@org.springframework.stereotype.Repository
public interface TransactionRepository extends CrudRepository<Transaction,Long> {
    Optional<Transaction> findByTransactionId(String transactionId);
}
