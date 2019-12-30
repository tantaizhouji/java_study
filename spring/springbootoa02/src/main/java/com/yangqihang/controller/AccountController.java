package com.yangqihang.controller;

import com.github.pagehelper.PageInfo;
import com.yangqihang.RespStat;
import com.yangqihang.entity.Account;
import com.yangqihang.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accSrv;

    /**
     * 跳转到用户列表
     *
     * @param pageNum  显示用户列表第几页
     * @param pageSize 显示用户列表每页显示数量
     * @param model
     * @return 跳转到templates目录下的/account/list
     */
    @RequestMapping("/list")
    public String list(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize, Model model) {
        PageInfo<Account> accPageInfo = accSrv.findAll(pageNum, pageSize);

        model.addAttribute("accPageInfo", accPageInfo);

        return "account/list";
    }

    /**
     * 跳转到用户登录页面
     *
     * @return 跳转到templates目录下的/account/login
     */
    @RequestMapping("/login")
    public String login() {
        return "account/login";
    }

    /**
     * 用户登录操作
     *
     * @param loginName 用户名
     * @param password  用户密码
     * @param request
     * @return 返回登录信息
     */
    @RequestMapping("/accountLogin")
    @ResponseBody
    public String accountLogin(String loginName, String password, HttpServletRequest request) {

        //匹配用户名和密码,返回匹配的账号
        Account account = accSrv.findByLoginNameAndPassword(loginName, password);

        if (null == account) {
            return "该用户尚未注册或者密码错误";
        }

        request.getSession().setAttribute("account", account);

        return "登录成功";
    }

    /**
     * 退出操作
     *
     * @param request
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("account");
        return "index";
    }

    /**
     * 用户注册页面
     *
     * @return
     */
    @RequestMapping("/register")
    public String register() {
        return "account/register";
    }

    /**
     * 用户注册操作
     *
     * @return
     */
    @RequestMapping("/accountRegister")
    @ResponseBody
    public RespStat accountRegister(Account account) {
        System.out.println("account: " + account.toString());
        RespStat stat = accSrv.add(account);

        return stat;
    }

    /**
     * 删除用户操作
     *
     * @param id
     * @return
     */
    @RequestMapping("/deleteById")
    @ResponseBody
    public RespStat deleteById(int id) {
        RespStat stat = accSrv.deleteById(id);

        return stat;
    }

    @RequestMapping("/update")
    @ResponseBody
    public RespStat update(int id, String password) {
        RespStat stat = accSrv.update(id, password);

        return stat;
    }
}
