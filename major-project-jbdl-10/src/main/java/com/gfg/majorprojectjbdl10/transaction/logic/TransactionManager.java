package com.gfg.majorprojectjbdl10.transaction.logic;

import com.gfg.majorprojectjbdl10.models.CreateTransactionRequest;
import com.gfg.majorprojectjbdl10.models.CreateTransactionResponse;
import com.gfg.majorprojectjbdl10.models.GetTransactionResponse;
import javassist.NotFoundException;

public interface TransactionManager {
    CreateTransactionResponse createTransaction(CreateTransactionRequest createTransactionRequest);
    GetTransactionResponse getTransaction(String transactionId) throws NotFoundException;
    void updateTransaction(String updateTransactionRequest);
}
