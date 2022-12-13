package com.digipay.ewallet.mappers;

import com.digipay.ewallet.models.user.dto.UserDto;
import com.digipay.ewallet.models.user.entity.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity dtoToEntityConvertor(UserDto userDto);
    UserDto entityToDtoConvertor(UserEntity userEntity);
    List<UserEntity> dtoToEntityConvertor(List<UserDto> userDto);
    List<UserDto> entityToDtoConvertor(List<UserEntity> userEntities);
}
