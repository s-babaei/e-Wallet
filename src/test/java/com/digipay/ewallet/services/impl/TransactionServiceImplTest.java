package com.digipay.ewallet.services.impl;

import com.digipay.ewallet.EWalletApplication;
import com.digipay.ewallet.enums.TransactionType;
import com.digipay.ewallet.enums.WalletEnum;
import com.digipay.ewallet.models.transaction.entity.TransactionEntity;
import com.digipay.ewallet.models.user.entity.UserEntity;
import com.digipay.ewallet.models.wallet.entity.WalletEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = EWalletApplication.class)
@EnableTransactionManagement
class TransactionServiceImplTest {

//    @Test
//    void saveTransaction() {
//    }

    @Autowired
    private TransactionServiceImpl transactionService;

    @BeforeEach
    void setUp() {


        WalletEntity wallet = new WalletEntity();
        wallet.setId(1L);
        wallet.setName("one");
        wallet.setStatus(WalletEnum.ACTIVE);
        wallet.setWalletNumber(122222L);
        wallet.setBalance(BigDecimal.valueOf(123000l));
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName("sara");
        userEntity.setLastName("babaei");
        userEntity.setUsername("sara");
        wallet.setUser(userEntity);
        TransactionEntity transaction = new TransactionEntity();
        transaction.setAmount(BigDecimal.valueOf(12l));
        transaction.setType(TransactionType.WITHDRAW);
        transaction.setDestinationWallet(1000l);
        transaction.setSourceWallet(2000l);
        transaction.setWallet(wallet);
    }
        @Test
        void save () {
            TransactionEntity transaction=new TransactionEntity();
            transaction.setAmount(BigDecimal.valueOf(120l));
            TransactionEntity saveTransaction = transactionService.saveTransaction(transaction);
            Long byId= transaction.getId();
            assertSame(saveTransaction.getId(), byId);

        }

}