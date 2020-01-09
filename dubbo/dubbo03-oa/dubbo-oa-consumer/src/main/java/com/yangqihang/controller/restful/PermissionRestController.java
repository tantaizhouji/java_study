package com.yangqihang.controller.restful;

import com.yangqihang.RespStat;
import com.yangqihang.entity.Permission;
import com.yangqihang.service.IPermissionService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * restful风格URI的controller
 */
@RestController
@RequestMapping("/api/v1/manager/permission")
public class PermissionRestController {

    @Reference(version = "1.0.0")
    private IPermissionService perSrv;

    /**
     * 权限添加接口
     *
     * @return
     */
    @RequestMapping("/add")
    public RespStat permissionAdd(Permission permission) {
        RespStat stat = perSrv.add(permission);

        return stat;
    }

    /**
     * 权限删除接口
     * @param id
     * @return
     */
    @RequestMapping("/deleteById")
    public RespStat deleteById(int id) {
        RespStat stat = perSrv.deleteById(id);

        return stat;
    }

    /**
     * 修改权限接口
     * @param permission
     * @return
     */
    @RequestMapping("/modify")
    public RespStat permissionModify(Permission permission) {
        RespStat stat = perSrv.modify(permission);

        return stat;
    }
}
