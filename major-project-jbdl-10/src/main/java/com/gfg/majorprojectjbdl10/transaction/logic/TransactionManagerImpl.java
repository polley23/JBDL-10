package com.gfg.majorprojectjbdl10.transaction.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gfg.majorprojectjbdl10.data.TransactionRepository;
import com.gfg.majorprojectjbdl10.entity.Transaction;
import com.gfg.majorprojectjbdl10.entity.TransactionStatus;
import com.gfg.majorprojectjbdl10.models.*;
import javassist.NotFoundException;
import lombok.SneakyThrows;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TransactionManagerImpl implements TransactionManager{
    @Autowired
    TransactionRepository transactionRepository;
    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    KafkaTemplate kafkaTemplate;

    @Override
    public CreateTransactionResponse createTransaction(CreateTransactionRequest createTransactionRequest) {
        Transaction transaction = Transaction
                .builder()
                .transactionId(UUID.randomUUID().toString())
                .amount(createTransactionRequest.getAmount())
                .recipient(createTransactionRequest.getRecipient())
                .payee(createTransactionRequest.getPayee())
                .status(TransactionStatus.PENDING)
                .type(createTransactionRequest.getType())
                .created_at(LocalDateTime.now())
                .updated_at(LocalDateTime.now())
                .build();

        Transaction persistedTransaction = transactionRepository.save(transaction);
        UpdateWalletRequest updateWalletRequest = UpdateWalletRequest
                .builder()
                .amount(transaction.getAmount())
                .payee(transaction.getPayee())
                .recipient(transaction.getRecipient())
                .transactionId(transaction.getTransactionId())
                .build();
        try {
            kafkaTemplate.send("walletUpdate",objectMapper.writeValueAsString(updateWalletRequest));
        } catch (JsonProcessingException e) {

        }

        return new CreateTransactionResponse(persistedTransaction.getTransactionId());
    }

    @Override
    public GetTransactionResponse getTransaction(String transactionId) throws NotFoundException {
        Transaction transaction = transactionRepository.findByTransactionId(transactionId)
                .orElseThrow(()-> new NotFoundException("Transaction is not valid id : "+transactionId));

        return GetTransactionResponse.builder()
                .amount(transaction.getAmount())
                .payee(transaction.getPayee())
                .recipient(transaction.getRecipient())
                .status(transaction.getStatus())
                .transactionId(transaction.getTransactionId())
                .build();
    }

    @SneakyThrows
    @Override
    @KafkaListener(topics = "updateTransaction",groupId = "tms")
    public void updateTransaction(String walletUpdateResponse) {
        WalletUpdateResponse response = objectMapper.readValue(walletUpdateResponse,WalletUpdateResponse.class);
        Transaction transaction = transactionRepository.findByTransactionId(response.getTransactionId())
                .orElseThrow(()-> new NotFoundException("Transaction is not valid id : "+response.getTransactionId()));

        transaction.setStatus(response.getTransactionStatus());
        transactionRepository.save(transaction);
        Notification notification1 = Notification.builder()
                .message("Your Transaction with id "+transaction.getTransactionId()+ "is "
                        + transaction.getStatus().toString() +" reason : "+response.getResult() )
                .subject(" Do not reply. Transaction : "+transaction.getStatus().toString())
                .user(transaction.getPayee())
                .build();
        kafkaTemplate.send("notification", objectMapper.writeValueAsString(notification1));


        if(transaction.getStatus().equals(TransactionStatus.SUCCESS)) {
            Notification notification2 = Notification.builder()
                    .message("Amount "+transaction.getAmount()+ "got credited from " + transaction.getPayee())
                    .subject(" Do not reply. Amount credited")
                    .user(transaction.getRecipient())
                    .build();
            kafkaTemplate.send("notification", objectMapper.writeValueAsString(notification2));
        }

    }
}
