package com.yangqihang.controller;

import com.yangqihang.entity.Account;
import com.yangqihang.entity.Role;
import com.yangqihang.service.AccountService;
import com.yangqihang.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accSrv;

    @Autowired
    private RoleService roleSrv;

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
     * 跳转到个人配置页面
     *
     * @return
     */
    @RequestMapping("/profile")
    public String profile() {
        return "account/profile";
    }

    /**
     * 上传文件操作
     *
     * @param fileName
     * @return
     */
    @RequestMapping("/fileUpload")
    public String fileUpload(@RequestParam MultipartFile fileName, HttpServletRequest request) {
        //获取当前登录账户
        Account account = (Account) request.getSession().getAttribute("account");

        accSrv.updateProfile(fileName,account);

        return "account/profile";
    }

    @RequestMapping("/modifyRole")
    public String modifyRole(@RequestParam(value = "id") int id, Model model) {
        Account account = accSrv.findById(id);
        List<Role> roleList = roleSrv.findAll();

        model.addAttribute("account", account);
        model.addAttribute("roleList", roleList);

        return "account/modifyRole";
    }
}
