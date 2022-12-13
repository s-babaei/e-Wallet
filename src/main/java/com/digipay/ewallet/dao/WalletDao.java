package com.digipay.ewallet.dao;


import com.digipay.ewallet.models.wallet.entity.WalletEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletDao extends JpaRepository<WalletEntity,Long> {

    <S extends WalletEntity> List<S> findAll(Example<S> example);

    WalletEntity findByWalletNumber(Long walletNumber);


}




