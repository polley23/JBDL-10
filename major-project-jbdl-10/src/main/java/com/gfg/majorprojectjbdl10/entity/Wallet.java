package com.gfg.majorprojectjbdl10.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    Float balance;
    @Column(updatable = false, insertable = false)
    private LocalDateTime created_at;
    @Column(updatable = false, insertable = false)
    private LocalDateTime updated_at;
    @ManyToMany(cascade = {CascadeType.REMOVE,CascadeType.DETACH,CascadeType.PERSIST},fetch = FetchType.EAGER)
    @JoinTable(name = "wallet_transaction_history",joinColumns = @JoinColumn(name = "wallet_id"),
    inverseJoinColumns = @JoinColumn(name ="transaction_id"))
    private List<WalletTransaction> walletTransactions;
}
