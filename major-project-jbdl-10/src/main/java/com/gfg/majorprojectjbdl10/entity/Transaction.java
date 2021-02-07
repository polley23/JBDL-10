package com.gfg.majorprojectjbdl10.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String transactionId;
    @Column(nullable = false)
    private Float  amount;
    @Column(nullable = false)
    private TransactionStatus status;
    @Column(nullable = false)
    private String payee;
    @Column(nullable = false)
    private String recipient;
    @Column(nullable = false)
    private TransactionType type;
    @Column(updatable = false, insertable = false)
    private LocalDateTime created_at;
    @Column(updatable = false, insertable = false)
    private LocalDateTime updated_at;
}
