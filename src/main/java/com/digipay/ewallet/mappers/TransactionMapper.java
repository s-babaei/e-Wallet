package com.digipay.ewallet.mappers;

import com.digipay.ewallet.models.transaction.dto.TransactionDto;
import com.digipay.ewallet.models.transaction.entity.TransactionEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionDto entityToDtoConvertor(TransactionEntity TransactionEntity);
    TransactionEntity dtoToEntityConvertor(TransactionDto transactionDto);
    List<TransactionDto> entityToDtoConvertor(List<TransactionEntity> TransactionEntity);
    List<TransactionEntity> dtoTpoEntityConvertor(List<TransactionDto> transactionDto);
}
