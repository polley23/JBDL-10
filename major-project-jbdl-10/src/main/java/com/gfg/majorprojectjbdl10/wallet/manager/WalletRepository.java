package com.gfg.majorprojectjbdl10.wallet.manager;

import com.gfg.majorprojectjbdl10.entity.Wallet;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface WalletRepository extends CrudRepository<Wallet,Long> {
    Optional<Wallet> findByUsername(String username);
}
