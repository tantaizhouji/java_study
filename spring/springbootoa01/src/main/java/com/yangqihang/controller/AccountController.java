package com.yangqihang.controller;

import com.yangqihang.entity.Account;
import com.yangqihang.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accSrv;

    @RequestMapping("/list")
    public String list(@RequestParam(defaultValue = "1") int pageNum,@RequestParam(defaultValue = "5") int pageSize, Model model) {
        List<Account> accList = accSrv.findAll(pageNum,pageSize);

        model.addAttribute("accList", accList);

        return "account/list";
    }

    @RequestMapping("/login")
    public String login() {
        return "account/login";
    }

    @RequestMapping("/checkAccount")
    @ResponseBody
    public String checkAccount(String loginName, String password, HttpServletRequest request) {
        Account account = accSrv.findByLoginNameAndPassword(loginName, password);

        if (null == account) {
            return "该用户尚未注册";
        }

        request.getSession().setAttribute("account", account);

        return "登录成功";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("account");
        return "index";
    }
}
