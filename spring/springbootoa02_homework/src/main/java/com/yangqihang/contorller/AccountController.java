package com.yangqihang.contorller;

import com.github.pagehelper.PageInfo;
import com.yangqihang.RespStat;
import com.yangqihang.entity.Account;
import com.yangqihang.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accSrv;

    /**
     * 登录页面
     *
     * @return
     */
    @RequestMapping("/login")
    public String login() {
        return "/account/login";
    }

    /**
     * 登录操作
     *
     * @return
     */
    @RequestMapping("/accountLogin")
    @ResponseBody
    public RespStat accountLogin(String loginName, String password, HttpServletRequest request) {
        RespStat stat = accSrv.findByLoginNameAndPassword(loginName, password);
        List<Account> accList = (List<Account>) stat.getData();

        if (!accList.isEmpty()) {
            //把登录账户加入到Session中
            Account account = accList.get(0);
            System.out.println(account);
            request.getSession().setAttribute("account", account);

            return stat;
        }

        return stat;
    }

    /**
     * 注册页面
     *
     * @return
     */
    @RequestMapping("/register")
    public String register() {
        return "/account/register";
    }

    @RequestMapping("/accountRegister")
    @ResponseBody
    public RespStat accountRegister(Account account) {
        RespStat stat = accSrv.add(account);

        return stat;
    }

    /**
     * 用户退出操作
     *
     * @param request
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("account");

        return "/index";
    }

    /**
     * 用户列表页面
     *
     * @return
     */
    @RequestMapping("/list")
    public String list(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize, ModelMap model) {
        PageInfo<Account> accPageInfo = accSrv.findAll(pageNum, pageSize);
        model.addAttribute("accPageInfo", accPageInfo);

        return "/account/list";
    }

    @RequestMapping("/deleteById")
    @ResponseBody
    public RespStat deleteById(int id, HttpServletRequest request) {
        //获取登录用户
        Account account = (Account) request.getSession().getAttribute("account");

        RespStat stat = accSrv.deleteById(id, account);

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
     * 修改权限
     *
     * @param id
     * @param role
     * @return
     */
    @RequestMapping("/updateRole")
    @ResponseBody
    public RespStat updateRole(int id, String role, HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute("account");

        RespStat stat = accSrv.updateRole(id, role, account);

        return stat;
    }

    /**
     * 个人信息页面
     *
     * @return
     */
    @RequestMapping("/profile")
    public String profile() {

        //测试上传文件的地址
        try {
            File uploads = new File("d:", "/java/uploads/");
            System.out.println(uploads.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/account/profile";
    }

    /**
     * 上传文件操作
     *
     * @return
     */
    @RequestMapping("/fileUpload")
    public String fileUpload(MultipartFile fileName) {

        //设置上传路径
        File uploads = new File("d:", "/java/uploads/");

        try {
            //保存上传文件
            fileName.transferTo(new File(uploads + "/" + fileName.getOriginalFilename()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/account/profile";
    }
}
