package com.yangqihang.controller;

import com.yangqihang.RespStat;
import com.yangqihang.entity.Account;
import com.yangqihang.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;


@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accSrv;

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

    /**
     * 修改密码
     *
     * @param id
     * @param password
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public RespStat update(int id, String password) {
        RespStat stat = accSrv.update(id, password);

        return stat;
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
    public String fileUpload(MultipartFile fileName, HttpServletRequest request) {
        //获取当前登录账户
        Account account = (Account) request.getSession().getAttribute("account");

        try {
            //文件转存
            fileName.transferTo(new File("D:/github/java_study/uploads/" + fileName.getOriginalFilename()));

            accSrv.updateProfile(fileName.getOriginalFilename(),account);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/account/profile";
    }
}
