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
class UserServiceImplTest {


    @Autowired
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {


        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName("sara");
        userEntity.setLastName("babaei");
        userEntity.setUsername("saraaa");


    }

    @Test
    void saveUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName("sara");
        UserEntity saveUser = userService.saveUser(userEntity);
        Long id = userEntity.getId();
        assertSame(saveUser.getId(), id);
    }


}