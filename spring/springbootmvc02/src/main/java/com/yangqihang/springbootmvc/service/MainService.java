package com.yangqihang.springbootmvc.service;

import com.yangqihang.springbootmvc.RespStat;
import com.yangqihang.springbootmvc.dao.MainDao;
import com.yangqihang.springbootmvc.entity.Account;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainService {

    @Autowired
    private MainDao dao;

    public RespStat add(Account account) {

        Account entity = null;

        try {
            entity = dao.save(account);
        } catch (Exception e) {
            return RespStat.build(500, "失败", e.getMessage());
        }

        return RespStat.build(200, "成功", entity.toString());
    }

    public List<Account> findAll() {
        List<Account> acclist = dao.findAll();

        return acclist;
    }
}
