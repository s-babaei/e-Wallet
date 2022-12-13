package com.digipay.ewallet.apis;

import com.digipay.ewallet.exceptions.GlobalException;
import com.digipay.ewallet.mappers.UserMapper;
import com.digipay.ewallet.models.IsNotNull;
import com.digipay.ewallet.models.user.dto.UserDto;
import com.digipay.ewallet.models.user.entity.UserEntity;
import com.digipay.ewallet.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserRestApis {

    @Autowired
    private UserService userService;


    @Autowired
    private UserMapper userMapper;


    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public List<UserDto> searchUser(@Validated(IsNotNull.class) @RequestBody UserDto userDtoFromClient) throws GlobalException {
        UserEntity userEntityAfterMapping = userMapper.dtoToEntityConvertor(userDtoFromClient);
        List<UserEntity> saveUsers = userService.searchByExample(userEntityAfterMapping);
        return userMapper.entityToDtoConvertor(saveUsers);

    }

    @PutMapping
    @PreAuthorize("hasRole('USER')")
    public UserDto updateUser(@Validated(IsNotNull.class) @RequestBody UserDto userDtoFromClient) throws GlobalException {
        UserEntity userEntityAfterMapping = userMapper.dtoToEntityConvertor(userDtoFromClient);
        UserEntity updateUser = userService.saveUser(userEntityAfterMapping);
        return userMapper.entityToDtoConvertor(updateUser);


    }


}
