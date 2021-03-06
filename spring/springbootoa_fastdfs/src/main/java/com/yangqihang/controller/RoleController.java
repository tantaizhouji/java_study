package com.yangqihang.controller;

import com.yangqihang.entity.Permission;
import com.yangqihang.entity.Role;
import com.yangqihang.service.PermissionService;
import com.yangqihang.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleSrv;

    @Autowired
    private PermissionService perSrv;

    /**
     * 跳转角色添加页面
     *
     * @return
     */
    @RequestMapping("/add")
    public String add() {
        return "role/add";
    }

    /**
     * 跳转角色配置页面
     *
     * @return
     */
    @RequestMapping("/modify")
    public String modify(@RequestParam(value = "id") int id, Model model) {
        Role role = roleSrv.findById(id);

        List<Permission> permissionList = perSrv.findAll();

        model.addAttribute("role", role);
        model.addAttribute("permissionList", permissionList);

        return "role/modify";
    }
}
