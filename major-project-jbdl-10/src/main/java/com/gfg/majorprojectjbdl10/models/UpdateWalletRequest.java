package com.gfg.majorprojectjbdl10.models;

import com.gfg.majorprojectjbdl10.entity.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UpdateWalletRequest {
    private Float amount;
    private String payee;
    private String recipient;
    private TransactionType type;
    private String transactionId;
}
