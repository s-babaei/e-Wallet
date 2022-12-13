package com.digipay.ewallet.services.impl;

import com.digipay.ewallet.exceptions.GlobalException;
import com.digipay.ewallet.dao.TransactionDao;
import com.digipay.ewallet.models.transaction.entity.TransactionEntity;
import com.digipay.ewallet.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    TransactionDao transactionDao;


    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public TransactionEntity saveTransaction(TransactionEntity transaction) throws GlobalException {
        return transactionDao.save(transaction);
    }

}
