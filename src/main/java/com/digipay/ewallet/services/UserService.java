package com.digipay.ewallet.services;

import com.digipay.ewallet.models.user.entity.UserEntity;
import org.springframework.data.domain.Example;

import java.util.List;

public interface UserService {



    UserEntity saveUser(UserEntity userEntity);
    List<UserEntity> searchByExample(UserEntity userEntity);
    UserEntity findByUserName(String username);


}
