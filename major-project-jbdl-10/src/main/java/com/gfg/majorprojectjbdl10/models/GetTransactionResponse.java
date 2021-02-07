package com.gfg.majorprojectjbdl10.models;

import com.gfg.majorprojectjbdl10.entity.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GetTransactionResponse {
    private Float amount;
    private TransactionStatus status;
    private String payee;
    private String recipient;
    private String transactionId;
}
