package com.yangqihang.service;

import com.yangqihang.entity.Account;
import com.yangqihang.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired(required = false)
    private AccountMapper accMapper;

    public List<Account> findAll() {
        List<Account> accList = accMapper.findAll();

        return accList;
    }
}
