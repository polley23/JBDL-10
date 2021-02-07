package com.gfg.majorprojectjbdl10.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class WalletTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String transactionId;
    @ManyToMany(mappedBy = "walletTransactions", cascade = {CascadeType.REMOVE,CascadeType.DETACH,CascadeType.PERSIST},fetch = FetchType.EAGER)
    private List<Wallet> wallets;
}
