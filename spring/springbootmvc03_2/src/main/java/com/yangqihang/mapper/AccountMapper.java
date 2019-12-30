package com.yangqihang.mapper;

import com.yangqihang.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AccountMapper {

    @Select("select * from account")
    List<Account> findAll();

    void add(Account account);
}
