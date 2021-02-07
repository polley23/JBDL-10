package com.gfg.majorprojectjbdl10.resources;

import com.gfg.majorprojectjbdl10.transaction.logic.TransactionManager;
import com.gfg.majorprojectjbdl10.models.CreateTransactionRequest;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionResources {
    @Autowired
    TransactionManager transactionManager;

    @PostMapping("/transaction")
    ResponseEntity createTransaction(@RequestBody CreateTransactionRequest createTransactionRequest, Authentication authentication){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),authentication.getCredentials());
        createTransactionRequest.setPayee(usernamePasswordAuthenticationToken.getName());
        return ResponseEntity.accepted().body(transactionManager.createTransaction(createTransactionRequest));
    }

    @GetMapping("/transaction/{transaction_id}/status")
    ResponseEntity getTransaction(@PathVariable("transaction_id") String transactionID ){
        try {
            return ResponseEntity.ok(transactionManager.getTransaction(transactionID));
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
