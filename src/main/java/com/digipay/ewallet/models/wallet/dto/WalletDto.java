package com.digipay.ewallet.models.wallet.dto;

import com.digipay.ewallet.models.IsNotNull;
import com.digipay.ewallet.models.transaction.entity.TransactionEntity;
import com.digipay.ewallet.models.user.entity.UserEntity;
import com.digipay.ewallet.enums.WalletEnum;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class WalletDto {


    private Long id;

    @NotBlank(message = "name should be necessary!", groups = IsNotNull.class)
    @Size(min = 3, max = 20, message = "wallet.name.invalid.size", groups = IsNotNull.class)
    private String name;


    private WalletEnum status;

    private BigDecimal balance;

    private Long walletNumber;

    private UserEntity user;

    private List<TransactionEntity> transactions = new ArrayList<>();

}
