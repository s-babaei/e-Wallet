package com.digipay.ewallet.services.impl;

import com.digipay.ewallet.exceptions.GlobalException;
import com.digipay.ewallet.dao.UserDao;
import com.digipay.ewallet.models.user.entity.UserEntity;
import com.digipay.ewallet.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    @Transactional
    public UserEntity saveUser(UserEntity userEntity) throws GlobalException {
        return userDao.save(userEntity);
    }

    @Override
    @Transactional
    public List<UserEntity> searchByExample(UserEntity userEntity) throws GlobalException {
        return userDao.findAll(Example.of(userEntity));
    }

    @Override
    @Transactional
    public UserEntity findByUserName(String username) {
        return userDao.findUserEntityByUsername(username);
    }


}
