package com.gfg.majorprojectjbdl10.wallet.manager;


import com.gfg.majorprojectjbdl10.models.WalletResponse;
import javassist.NotFoundException;

public interface WalletManager {
    void createWallet(String createWalletRequest);
    void updateWallet(String updateWalletRequest);
    WalletResponse getWallet(String username) throws NotFoundException;
}
