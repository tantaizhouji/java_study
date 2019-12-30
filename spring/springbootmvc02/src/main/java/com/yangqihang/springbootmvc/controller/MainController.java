package com.yangqihang.springbootmvc.controller;

import com.yangqihang.springbootmvc.RespStat;
import com.yangqihang.springbootmvc.entity.Account;
import com.yangqihang.springbootmvc.service.MainService;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/account")
public class MainController {

    @Autowired
    private MainService srv;

    @RequestMapping("/list")
    public String list(ModelMap map) {
        List<Account> acclist = srv.findAll();

        map.addAttribute("acclist", acclist);

        return "list";
    }

    @GetMapping("/register")
    public String registerGet() {
        System.out.println("get");

        return "register";
    }

    @PostMapping("/register")
    public String registerPost(HttpServletRequest request, Account account) {
        String loginName = request.getParameter("loginName");
        System.out.println("loginName: " + loginName);
        System.out.println("account: " + ToStringBuilder.reflectionToString(account));

        RespStat stat = srv.add(account);
        request.setAttribute("stat", stat);

        return "register";
    }
}
