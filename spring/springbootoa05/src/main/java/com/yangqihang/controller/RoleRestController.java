package com.yangqihang.controller;

import com.yangqihang.RespStat;
import com.yangqihang.entity.Role;
import com.yangqihang.service.RoleService;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/manager/role")
public class RoleRestController {

    @Autowired
    private RoleService roleSrv;

    /**
     * 角色添加接口
     *
     * @param role
     * @return
     */
    @RequestMapping("/add")
    public RespStat roleAdd(Role role) {
        RespStat stat = roleSrv.add(role);

        return stat;
    }

    /**
     * 角色删除接口
     *
     * @param id
     * @return
     */
    @RequestMapping("/deleteById")
    public RespStat deleteById(int id) {
        RespStat stat = roleSrv.deleteById(id);

        return stat;
    }

    /**
     * 角色名称修改接口
     *
     * @param role
     * @return
     */
    @RequestMapping("/modifyName")
    public RespStat modifyName(Role role) {
        RespStat stat = roleSrv.modifyName(role);

        return stat;
    }

    @RequestMapping("/modifyPermissions")
    public RespStat modifyPermissions(@RequestParam(required = false) int[] permissions, @RequestParam int id) {
        RespStat stat = roleSrv.modifyPermissions(id, permissions);

        return stat;
    }
}
