package com.yangqihang.mapper;

import com.yangqihang.entity.Account;
import com.yangqihang.entity.AccountExample;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * AccountMapper继承基类
 */
@Mapper
@Repository
public interface AccountMapper extends MyBatisBaseDao<Account, Integer, AccountExample> {
}