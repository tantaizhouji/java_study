package com.yangqihang.controller;

import com.yangqihang.RespStat;
import com.yangqihang.entity.Permission;
import com.yangqihang.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService perSrv;

    /**
     * 权限添加页面
     *
     * @return
     */
    @RequestMapping("/add")
    public String add() {
        return "permission/add";
    }

    /**
     * 权限添加接口
     *
     * @return
     */
    @RequestMapping("/permissionAdd")
    @ResponseBody
    public RespStat permissionAdd(Permission permission) {
        RespStat stat = perSrv.add(permission);

        return stat;
    }

    @RequestMapping("/deleteById")
    @ResponseBody
    public RespStat deleteById(int id) {
        RespStat stat = perSrv.deleteById(id);

        return stat;
    }

    /**
     * 跳转权限修改页面
     *
     * @param id
     * @return
     */
    @RequestMapping("/modify")
    public String modify(@RequestParam(value = "id") int id, Model model) {
        Permission permission = perSrv.findById(id);

        model.addAttribute("permission",permission);

        return "/permission/modify";
    }

    /**
     * 修改权限操作
     * @param permission
     * @return
     */
    @RequestMapping("/permissionModify")
    @ResponseBody
    public RespStat permissionModify(Permission permission) {
        RespStat stat = perSrv.modify(permission);

        return stat;
    }
}
