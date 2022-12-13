package com.digipay.ewallet.models.transaction.dto;

import com.digipay.ewallet.models.IsNotNull;
import com.digipay.ewallet.enums.TransactionEnum;
import com.digipay.ewallet.enums.TransactionType;
import com.digipay.ewallet.models.wallet.entity.WalletEntity;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Date;


@Data
public class TransactionDto {




    private Long id;

    private Date createDate;

    private WalletEntity Wallet;

    private TransactionEnum status;

    private TransactionType type;

    @NotEmpty(groups =IsNotNull.class,message ="amount is necessary field!")
    @Range(groups =IsNotNull.class,message ="amount should not be zero")
    private BigDecimal amount;

    @NotEmpty(groups = IsNotNull.class)
    private Long source;

    @NotEmpty(groups = IsNotNull.class)
    private Long destination;



}
