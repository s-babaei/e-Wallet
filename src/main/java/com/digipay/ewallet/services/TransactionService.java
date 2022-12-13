package com.digipay.ewallet.services;

import com.digipay.ewallet.exceptions.GlobalException;
import com.digipay.ewallet.models.transaction.entity.TransactionEntity;

import java.util.List;

public interface TransactionService {

  TransactionEntity saveTransaction(TransactionEntity transaction) throws GlobalException;



    ;

}
