package com.digipay.ewallet.services;

import com.digipay.ewallet.models.transaction.entity.TransactionEntity;
import com.digipay.ewallet.models.wallet.entity.WalletEntity;

import java.util.List;

public interface WalletService {


    WalletEntity saveWallet(WalletEntity wallet);


    WalletEntity updateWallet(WalletEntity wallet);

    void deleteWallet(Long id);

    TransactionEntity withdrawFromWallet(TransactionEntity transaction);

    TransactionEntity depositToWallet(TransactionEntity transaction);

    TransactionEntity walletToWallet(TransactionEntity transaction);

}
