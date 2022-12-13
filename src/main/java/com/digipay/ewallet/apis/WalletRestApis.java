package com.digipay.ewallet.apis;

import com.digipay.ewallet.exceptions.GlobalException;
import com.digipay.ewallet.mappers.TransactionMapper;
import com.digipay.ewallet.mappers.WalletMapper;
import com.digipay.ewallet.models.IsNotNull;
import com.digipay.ewallet.models.transaction.entity.TransactionEntity;
import com.digipay.ewallet.models.transaction.dto.TransactionDto;
import com.digipay.ewallet.models.wallet.dto.WalletDto;
import com.digipay.ewallet.models.wallet.entity.WalletEntity;
import com.digipay.ewallet.services.TransactionService;
import com.digipay.ewallet.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/wallets")
public class WalletRestApis {

    @Autowired
    WalletService walletService;

    @Autowired
    WalletMapper walletMapper;

    @Autowired
    TransactionMapper transactionMapper;

    @Autowired
    TransactionService transactionService;

    @PostMapping("/save")
    @PreAuthorize("hasRole('USER')")
    WalletDto saveWallet(@RequestBody @Validated(IsNotNull.class) WalletDto walletDto) throws GlobalException {
        WalletEntity walletEntityAfterMapping = walletMapper.dtoToEntityConvertor(walletDto);
        WalletEntity saveWallet = walletService.saveWallet(walletEntityAfterMapping);
        return walletMapper.entityToDtoConvertor(saveWallet);


    }


    @PutMapping
    @PreAuthorize("hasRole('USER')")
    public WalletDto updateWallet(@Validated(IsNotNull.class) WalletDto walletDtoFromClient) throws GlobalException {
        WalletEntity walletEntityAfterMapping = walletMapper.dtoToEntityConvertor(walletDtoFromClient);
        WalletEntity updateWallet = walletService.updateWallet(walletEntityAfterMapping);
        return walletMapper.entityToDtoConvertor(updateWallet);


    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public void deleteWallet(@Validated(IsNotNull.class) @PathVariable("id") Long id) throws GlobalException {
        walletService.deleteWallet(id);
    }

    @PostMapping("/deposit")
    @PreAuthorize("hasRole('USER')")
    public TransactionDto depositToWallet(@Validated(IsNotNull.class) @RequestBody TransactionDto transactionDtoFromClient) throws GlobalException {
        TransactionEntity transactionEntity = transactionMapper.dtoToEntityConvertor(transactionDtoFromClient);
        TransactionEntity deposit = walletService.depositToWallet(transactionEntity);
        return transactionMapper.entityToDtoConvertor(deposit);


    }


    @PostMapping("/withdraw")
    @PreAuthorize("hasRole('USER')")
    public TransactionDto withdrawFromWallet(@Validated(IsNotNull.class) @RequestBody TransactionDto transactionDtoFromClient) throws GlobalException {
        TransactionEntity transactionEntity = transactionMapper.dtoToEntityConvertor(transactionDtoFromClient);
        TransactionEntity withdraw = walletService.withdrawFromWallet(transactionEntity);
        return transactionMapper.entityToDtoConvertor(withdraw);

    }

    @PostMapping("/transfer")
    @PreAuthorize("hasRole('USER')")
    public TransactionDto walletToWallet(@Validated(IsNotNull.class) @RequestBody TransactionDto transactionDtoFromClient) throws GlobalException {
        TransactionEntity transactionEntity = transactionMapper.dtoToEntityConvertor(transactionDtoFromClient);
        TransactionEntity transfer = walletService.walletToWallet(transactionEntity);
        return transactionMapper.entityToDtoConvertor(transfer);
    }

}

