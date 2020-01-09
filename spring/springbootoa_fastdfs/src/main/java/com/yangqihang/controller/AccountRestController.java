package com.yangqihang.controller;

import com.yangqihang.RespStat;
import com.yangqihang.entity.Account;
import com.yangqihang.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/manager/account")
public class AccountRestController {

    @Autowired
    private AccountService accSrv;

    /**
     * 用户登录操作接口
     *
     * @param loginName 用户名
     * @param password  用户密码
     * @param request
     * @return 返回登录信息
     */
    @RequestMapping("/accountLogin")
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
     * 用户注册操作接口
     *
     * @return
     */
    @RequestMapping("/accountRegister")
    public RespStat accountRegister(Account account) {
        RespStat stat = accSrv.add(account);

        return stat;
    }

    /**
     * 删除用户操作接口
     *
     * @param id
     * @return
     */
    @RequestMapping("/deleteById")
    public RespStat deleteById(int id) {
        RespStat stat = accSrv.deleteById(id);

        return stat;
    }

    /**
     * 修改密码接口
     *
     * @param id
     * @param password
     * @return
     */
    @RequestMapping("/update")
    public RespStat update(int id, String password) {
        RespStat stat = accSrv.update(id, password);

        return stat;
    }

    @RequestMapping("/modifyRoles")
    public RespStat modifyRoles(@RequestParam(required = false) int[] roles, @RequestParam int id) {
        RespStat stat = accSrv.modifyRoles(roles, id);

        return stat;
    }
}
