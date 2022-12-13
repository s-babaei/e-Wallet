package com.digipay.ewallet.dao;


import com.digipay.ewallet.models.transaction.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDao extends JpaRepository<TransactionEntity,Long> {


}
