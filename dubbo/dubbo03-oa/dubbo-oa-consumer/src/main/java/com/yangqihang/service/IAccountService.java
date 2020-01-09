package com.yangqihang.service;

import com.github.pagehelper.PageInfo;
import com.yangqihang.RespStat;
import com.yangqihang.entity.Account;

public interface IAccountService {
    Account findByLoginNameAndPassword(String loginName, String password);

    PageInfo<Account> findAll(int pageNum, int pageSize);

    RespStat add(Account account);

    RespStat deleteById(int id);

    RespStat update(int id, String password);

    void updateProfile(String originalFilename, Account account);

    Account findById(int id);

    RespStat modifyRoles(int[] roles, int id);
}
