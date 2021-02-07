package com.gfg.majorprojectjbdl10.resources;


import com.gfg.majorprojectjbdl10.wallet.manager.WalletManager;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WalletResource {
    @Autowired
    private WalletManager walletManager;

    @GetMapping("/wallet/{username}")
    ResponseEntity get(@PathVariable("username") String username){
        try {
            return  ResponseEntity.ok(walletManager.getWallet(username));
        } catch (NotFoundException e) {
            return  ResponseEntity.ok(e.getMessage());
        }
    }
}
