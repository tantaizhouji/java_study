package com.yangqihang.controller;

import com.yangqihang.entity.Account;
import com.yangqihang.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {

    @Autowired
    private AccountService accSrv;

    @RequestMapping("/list")
    public String list() {
        List<Account> accList = accSrv.findAll();

        return accList.toString();
    }
}
