package com.gfg.majorprojectjbdl10.wallet.manager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gfg.majorprojectjbdl10.entity.TransactionStatus;
import com.gfg.majorprojectjbdl10.entity.Wallet;
import com.gfg.majorprojectjbdl10.entity.WalletTransaction;
import com.gfg.majorprojectjbdl10.models.UpdateWalletRequest;
import com.gfg.majorprojectjbdl10.models.WalletResponse;
import com.gfg.majorprojectjbdl10.models.WalletUpdateResponse;
import javassist.NotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

@Service
public class WalletManagerImpl implements WalletManager {
    @Autowired
    WalletRepository walletRepository;

    @Autowired
    KafkaTemplate kafkaTemplate;

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @KafkaListener(topics = "walletCreation", groupId = "wallet")
    public void createWallet(String username) {
        Wallet wallet = Wallet.builder()
                .balance(0f)
                .created_at(LocalDateTime.now())
                .updated_at(LocalDateTime.now())
                .username(username)
                .build();
        walletRepository.save(wallet);

    }

    @SneakyThrows
    @Override
    @KafkaListener(topics = "walletUpdate", groupId = "wallet")
    public void updateWallet(String updateWalletRequest) {
        UpdateWalletRequest request =  objectMapper.readValue(updateWalletRequest,UpdateWalletRequest.class);
        WalletUpdateResponse walletUpdateResponse = WalletUpdateResponse.builder()
                .transactionId(request.getTransactionId())
                .build();
        try {
            Wallet payeeWallet = walletRepository.findByUsername(request.getPayee())
                    .orElseThrow(() -> new NotFoundException("wallet not found"));
            Wallet recipientWallet = walletRepository.findByUsername(request.getRecipient())
                    .orElseThrow(() -> new NotFoundException("wallet not found"));
            if(payeeWallet.getBalance()<request.getAmount()){
                throw new Exception("no balance");
            }
            payeeWallet.setBalance(payeeWallet.getBalance()-request.getAmount());
            recipientWallet.setBalance(recipientWallet.getBalance()+request.getAmount());

            WalletTransaction walletTransaction = WalletTransaction.builder()
                    .transactionId(request.getTransactionId()).build();

            if(Objects.isNull(payeeWallet.getWalletTransactions())){
                payeeWallet.setWalletTransactions(Arrays.asList(walletTransaction) );
            }else{
                payeeWallet.getWalletTransactions().add(walletTransaction);
            }

            if(Objects.isNull(recipientWallet.getWalletTransactions())){
                recipientWallet.setWalletTransactions(Arrays.asList(walletTransaction) );
            }else{
                recipientWallet.getWalletTransactions().add(walletTransaction);
            }

            walletTransaction.setWallets(Arrays.asList(payeeWallet,recipientWallet));

            walletRepository.save(payeeWallet);
            walletRepository.save(recipientWallet);

            walletUpdateResponse.setTransactionStatus(TransactionStatus.SUCCESS);
            walletUpdateResponse.setResult("transaction successful");

        }catch (Exception exception){
            walletUpdateResponse.setTransactionStatus(TransactionStatus.FAILURE);
            walletUpdateResponse.setResult(exception.getMessage());
        }
        kafkaTemplate.send("updateTransaction",objectMapper.writeValueAsString(walletUpdateResponse));

    }

    @Override
    public WalletResponse getWallet(String username) throws NotFoundException {
        Wallet wallet = walletRepository.findByUsername(username)
                .orElseThrow(()-> new NotFoundException("wallet not found"));
        return WalletResponse.builder()
                .balance(wallet.getBalance())
                .username(username)
                .build();
    }
}
