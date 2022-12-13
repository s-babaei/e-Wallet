package com.digipay.ewallet.mappers;

import com.digipay.ewallet.models.wallet.dto.WalletDto;
import com.digipay.ewallet.models.wallet.entity.WalletEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WalletMapper {

    WalletEntity dtoToEntityConvertor(WalletDto walletDto);
    WalletDto entityToDtoConvertor(WalletEntity walletEntity);
    List<WalletEntity> dtoToEntityConvertor(List<WalletDto> walletDto);
    List<WalletDto> entityToDtoConvertor(List<WalletEntity> walletEntity);
}
