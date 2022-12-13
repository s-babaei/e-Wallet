package com.digipay.ewallet.services.impl;


import com.digipay.ewallet.exceptions.GlobalException;
import com.digipay.ewallet.models.transaction.entity.TransactionEntity;
import com.digipay.ewallet.enums.TransactionEnum;
import com.digipay.ewallet.enums.TransactionType;
import com.digipay.ewallet.models.user.entity.UserEntity;
import com.digipay.ewallet.dao.WalletDao;
import com.digipay.ewallet.models.wallet.entity.WalletEntity;
import com.digipay.ewallet.enums.WalletEnum;
import com.digipay.ewallet.services.TransactionService;
import com.digipay.ewallet.services.UserService;
import com.digipay.ewallet.services.WalletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.List;


@Service
public class WalletServiceImpl implements WalletService {


    private final static Logger LOGGER = LoggerFactory.getLogger(WalletServiceImpl.class);

    @Autowired
    WalletDao walletDao;

    @Autowired
    TransactionService transactionService;

    @Autowired
    UserService userService;

    @Override
    @Transactional
    public TransactionEntity walletToWallet(TransactionEntity transaction) {


        BigDecimal amount = transaction.getAmount();
        WalletEntity sourceWallet = walletDao.findByWalletNumber(transaction.getSourceWallet());
        WalletEntity destinationWallet = walletDao.findByWalletNumber(transaction.getDestinationWallet());


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserEntity userByUsername = userService.findByUserName(username);

        if (sourceWallet != null && destinationWallet != null) {
            if (sourceWallet.getStatus().equals(WalletEnum.ACTIVE) && destinationWallet.getStatus().equals(WalletEnum.ACTIVE)) {
                if (userByUsername.getId().equals(sourceWallet.getUser().getId())) {
                    if (sourceWallet.getBalance().compareTo(amount) > -1) {
                        sourceWallet.setBalance(sourceWallet.getBalance().subtract(amount));
                        destinationWallet.setBalance(destinationWallet.getBalance().add(amount));
                        walletDao.save(sourceWallet);
                        walletDao.save(destinationWallet);
                        transaction.setType(TransactionType.TRANSFER);
                        transaction.setStatus(TransactionEnum.SUCCESS);
                        return transactionService.saveTransaction(transaction);


                    } else {
                        transaction.setStatus(TransactionEnum.FAIL);
                        transaction.setType(TransactionType.TRANSFER);
                        transaction.setWallet(sourceWallet);
                        transactionService.saveTransaction(transaction);
                        LOGGER.info(transaction.toString());
                        throw new GlobalException("sorry!You dont have enough balance!");
                    }
                } else {
                    transaction.setStatus(TransactionEnum.FAIL);
                    transaction.setType(TransactionType.TRANSFER);
                    transaction.setWallet(sourceWallet);
                    transactionService.saveTransaction(transaction);
                    LOGGER.info(transaction.toString());
                    throw new GlobalException("sorry!This wallet is not Yours!");
                }
            } else {
                transaction.setStatus(TransactionEnum.FAIL);
                transaction.setType(TransactionType.TRANSFER);
                transaction.setWallet(sourceWallet);
                transactionService.saveTransaction(transaction);
                LOGGER.info(transaction.toString());
                throw new GlobalException("sorry!This wallet is not active!!");
            }
        } else {
            transaction.setStatus(TransactionEnum.FAIL);
            transaction.setType(TransactionType.TRANSFER);
            transaction.setWallet(sourceWallet);
            transactionService.saveTransaction(transaction);
            LOGGER.info(transaction.toString());
            throw new GlobalException("sorry!This wallet is not found!");
        }

    }

    @Override
    @Transactional
    public TransactionEntity withdrawFromWallet(TransactionEntity transaction) throws GlobalException {
        Long sourceWallet = transaction.getSourceWallet();
        WalletEntity findWalletNumber = walletDao.findByWalletNumber(sourceWallet);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserEntity userByUsername = userService.findByUserName(username);

        if (findWalletNumber != null) {
            if (findWalletNumber.getStatus().equals(WalletEnum.ACTIVE)) {
                if (userByUsername.getId().equals(findWalletNumber.getUser().getId())) {
                    if (findWalletNumber.getBalance().compareTo(transaction.getAmount()) > -1) {
                        findWalletNumber.setBalance(findWalletNumber.getBalance().subtract(transaction.getAmount()));
                        walletDao.save(findWalletNumber);
                        transaction.setType(TransactionType.WITHDRAW);
                        transaction.setStatus(TransactionEnum.SUCCESS);
                        transaction.setWallet(findWalletNumber);
                        return transactionService.saveTransaction(transaction);

                    } else {
                        throw new GlobalException("Sorry!You dont have enough balance!");
                    }

                } else {
                    throw new GlobalException("Sorry!This wallet is not Yours!");
                }
            } else {
                throw new GlobalException("Sorry!Your wallet is not active!");
            }
        } else {
            throw new GlobalException("Sorry!Your wallet not found!");
        }

    }

    @Override
    @Transactional
    public TransactionEntity depositToWallet(TransactionEntity transaction) throws GlobalException {

        Long destinationWallet = transaction.getDestinationWallet();
        WalletEntity findWalletNumber = walletDao.findByWalletNumber(destinationWallet);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserEntity userByUsername = userService.findByUserName(username);
        if (findWalletNumber != null) {
            if (findWalletNumber.getStatus().equals(WalletEnum.ACTIVE)) {
                if (userByUsername.getId().equals(findWalletNumber.getUser().getId())) {
                    findWalletNumber.setBalance(findWalletNumber.getBalance().add(transaction.getAmount()));
                    walletDao.save(findWalletNumber);
                    transaction.setStatus(TransactionEnum.SUCCESS);
                    transaction.setType(TransactionType.DEPOSIT);
                    transaction.setWallet(findWalletNumber);
                    return transactionService.saveTransaction(transaction);

                } else {
                    throw new GlobalException("sorry!wallet number is not Yours!");
                }
            } else {

                throw new GlobalException("sorry!wallet is not active!");
            }
        } else {
            throw new GlobalException("sorry!wallet number is not found!");

        }
    }


    @Override
    @Transactional
    public WalletEntity saveWallet(WalletEntity wallet) throws GlobalException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserEntity findUserByUsername = userService.findByUserName(username);
        wallet.setUser(findUserByUsername);
        SecureRandom secureRandom = new SecureRandom();
        wallet.setWalletNumber(secureRandom.nextLong());
        wallet.setStatus(WalletEnum.ACTIVE);
        wallet.setBalance(BigDecimal.valueOf(0));
        return walletDao.save(wallet);
    }


    @Override
    @Transactional
    public WalletEntity updateWallet(WalletEntity wallet) throws GlobalException {
        return walletDao.save(wallet);
    }

    @Override
    @Transactional
    public void deleteWallet(Long id) throws GlobalException {
        try {
            walletDao.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error("not found", e, "not found");
            throw new GlobalException("Entity.Not.Found", e.getCause(), "EmptyResultDataAccessException");
        }
    }
}



