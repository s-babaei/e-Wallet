package com.digipay.ewallet.models.transaction.entity;


import com.digipay.ewallet.models.IsNotNull;
import com.digipay.ewallet.enums.TransactionEnum;
import com.digipay.ewallet.enums.TransactionType;
import com.digipay.ewallet.models.wallet.entity.WalletEntity;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TRANSACTION")
public class TransactionEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;


    @NotEmpty(groups = IsNotNull.class)
    @Column(name = "DESTINATION")
    private Long destinationWallet;


    @NotEmpty(groups = IsNotNull.class)
    @Column(name = "SOURCE")
    private Long sourceWallet;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createDate;


    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private TransactionEnum status;

    @Column(name ="AMOUNT")
    @NotEmpty(groups =IsNotNull.class,message ="amount is necessary field!")
    @Range(groups =IsNotNull.class,message ="amount should not be zero")
    private BigDecimal amount;

    @NotNull(groups = IsNotNull.class)
    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private TransactionType type;


    @ManyToOne
    private WalletEntity wallet;


}
