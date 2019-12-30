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

    public void add() {
        Account account = new Account();
        account.setLoginName("李典");
        account.setPassword("123");
        account.setNickName("胖子");
        account.setAge(18);
        account.setLocation("中国");

        accMapper.add(account);
    }
}
