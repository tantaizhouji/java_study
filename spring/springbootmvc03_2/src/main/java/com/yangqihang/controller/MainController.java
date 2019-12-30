package com.yangqihang.controller;

import com.yangqihang.entity.Account;
import com.yangqihang.entity.Menu;
import com.yangqihang.service.AccountService;
import com.yangqihang.service.MenuService;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {

    @Autowired
    private AccountService accSrv;

    @Autowired
    private MenuService menuSrv;

    @RequestMapping("/list")
    public Object list() {
        List<Account> accList = accSrv.findAll();

        List<Menu> menuList = menuSrv.findAll();

        return menuList;
    }

    @RequestMapping("/add")
    public String add() {
        accSrv.add();

        return "ok";
    }

    @RequestMapping("/addMenu")
    public String addMenu() {
        menuSrv.add();

        return "ok";
    }

    @RequestMapping("/page")
    public Object page(@RequestParam(required = false) Integer pageNum, @RequestParam(required = false) Integer pageSize) {
        List<Menu> menuList = menuSrv.findByPage(pageNum,pageSize);

        return menuList;
    }
}
