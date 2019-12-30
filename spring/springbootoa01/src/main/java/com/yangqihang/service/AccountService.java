package com.yangqihang.service;

import com.github.pagehelper.PageHelper;
import com.yangqihang.entity.Account;
import com.yangqihang.entity.AccountExample;
import com.yangqihang.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountMapper accMapper;

    public Account findByLoginNameAndPassword(String loginName, String password) {
        AccountExample example = new AccountExample();
        example.createCriteria().andLoginNameEqualTo(loginName).andPasswordEqualTo(password);
        List<Account> accList = accMapper.selectByExample(example);

        if (accList.isEmpty()) {
            return null;
        }

        return accList.get(0);
    }

    public List<Account> findAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        AccountExample example = new AccountExample();
        List<Account> accList = accMapper.selectByExample(example);

        return accList;
    }
}
