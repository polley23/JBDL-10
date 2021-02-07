package com.gfg.majorprojectjbdl10.models;

import com.gfg.majorprojectjbdl10.entity.TransactionStatus;
import com.gfg.majorprojectjbdl10.entity.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateTransactionRequest {
    private Float amount;
    private String payee;
    private String recipient;
    private TransactionType type;
}
