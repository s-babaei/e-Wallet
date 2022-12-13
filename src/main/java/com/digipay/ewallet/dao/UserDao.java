package com.digipay.ewallet.dao;

import com.digipay.ewallet.models.user.entity.UserEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UserDao extends JpaRepository<UserEntity, Long> {


    <S extends UserEntity> List<S> findAll(Example<S> example);

    UserEntity findUserEntityByUsername(String username);
}
