package com.yangqihang.mapper;

import com.yangqihang.entity.Account;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountMapper {
    List<Account> findAll();
}
