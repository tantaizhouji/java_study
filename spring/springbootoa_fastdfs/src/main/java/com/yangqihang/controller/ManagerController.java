package com.yangqihang.controller;

import com.github.pagehelper.PageInfo;
import com.yangqihang.entity.Account;
import com.yangqihang.entity.Permission;
import com.yangqihang.entity.Role;
import com.yangqihang.service.AccountService;
import com.yangqihang.service.PermissionService;
import com.yangqihang.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private AccountService accSrv;

    @Autowired
    private RoleService roleSrv;

    @Autowired
    private PermissionService perSrv;

    /**
     * 跳转到员工列表
     *
     * @param pageNum  显示员工列表第几页
     * @param pageSize 显示员工列表每页显示数量
     * @param model
     * @return 跳转到templates目录下的/manager/accountList
     */
    @RequestMapping("/accountList")
    public String accountList(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize, Model model) {
        PageInfo<Account> accPageInfo = accSrv.findAll(pageNum, pageSize);

        model.addAttribute("accPageInfo", accPageInfo);

        return "manager/accountList";
    }

    /**
     * 跳转到角色列表
     *
     * @param pageNum
     * @param pageSize
     * @param model
     * @return
     */
    @RequestMapping("/roleList")
    public String roleList(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize, Model model) {
        PageInfo<Role> rolePageInfo = roleSrv.findAll(pageNum, pageSize);

        model.addAttribute("rolePageInfo", rolePageInfo);

        return "manager/roleList";
    }

    /**
     * 跳转到权限列表
     *
     * @param pageNum
     * @param pageSize
     * @param model
     * @return
     */
    @RequestMapping("/permissionList")
    public String permissionList(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize, Model model) {
        PageInfo<Permission> perPageInfo = perSrv.findAll(pageNum, pageSize);

        model.addAttribute("perPageInfo", perPageInfo);

        return "manager/permissionList";
    }
}
