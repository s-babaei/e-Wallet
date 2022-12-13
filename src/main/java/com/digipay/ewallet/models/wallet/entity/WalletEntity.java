package com.digipay.ewallet.models.wallet.entity;


import com.digipay.ewallet.models.BaseEntity;
import com.digipay.ewallet.models.IsNotNull;

import com.digipay.ewallet.models.transaction.entity.TransactionEntity;
import com.digipay.ewallet.models.user.entity.UserEntity;
import com.digipay.ewallet.enums.WalletEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "WALLET")
public class WalletEntity extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;


    @Size(min = 3 , max = 20 , message = "wallet.name.invalid.size" , groups = IsNotNull.class)
    @Column(name = "FIRST_NAME")
    @NotBlank(message ="name is necessary!")
    private String name;



    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private WalletEnum status;



    @Column(name ="BALANCE")
    private BigDecimal balance;


    @Column(name = "WALLET_NUMBER",updatable = true)
    private Long walletNumber;


    @ManyToOne
    private UserEntity user;

    @OneToMany(mappedBy ="wallet")
    private List<TransactionEntity> transactions=new ArrayList<>();


}
