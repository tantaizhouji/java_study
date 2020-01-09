package com.yangqihang.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yangqihang.RespStat;
import com.yangqihang.entity.Permission;
import com.yangqihang.entity.Role;
import com.yangqihang.entity.RoleExample;
import com.yangqihang.mapper.RoleMapper;
import com.yangqihang.service.IRoleService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Service(version = "1.0.0",timeout = 10000,interfaceClass = IRoleService.class)
@Component
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public PageInfo<Role> findAll(int pageNum, int pageSize) {

        PageHelper.startPage(pageNum, pageSize);

        //查询角色列表
        RoleExample example = new RoleExample();
        List<Role> roleList = roleMapper.selectByExample(example);
        for(Role role : roleList) {
            //设置角色的权限列表
            List<Permission> role_permissionList = roleMapper.findPermissionsById(role.getId());
            role.setPermissionList(role_permissionList);
        }

        PageInfo<Role> rolePageInfo = new PageInfo<>(roleList, 5);

        return rolePageInfo;
    }

    @Override
    public List<Role> findAll() {
        RoleExample example = new RoleExample();
        List<Role> roleList = roleMapper.selectByExample(example);

        //查找每个角色的权限列表
        for(Role role : roleList) {
            List<Permission> role_permissionList = roleMapper.findPermissionsById(role.getId());
            role.setPermissionList(role_permissionList);
        }

        return roleList;
    }

    @Override
    public RespStat add(Role role) {
        //判断名称是否为空
        String name = role.getName();
        if ("".equals(name) || null == name) {
            return RespStat.build(400, "名称不能为空");
        }

        //是否存在相同名称的角色
        RoleExample example = new RoleExample();
        example.createCriteria().andNameEqualTo(name);
        List<Role> existRoles = roleMapper.selectByExample(example);
        if (!existRoles.isEmpty()) {
            return RespStat.build(400, "已存在相同名称的角色");
        }

        int row = roleMapper.insertSelective(role);

        if (0 == row) {
            return RespStat.build(400, "系统出错，添加失败");
        }

        return RespStat.build(200, "添加角色成功", role);
    }

    @Override
    public RespStat deleteById(int id) {
        if (1 > id) {
            return RespStat.build(500, "id非法");
        }

        int row = roleMapper.deleteByPrimaryKey(id);

        if (0 == row) {
            return RespStat.build(400, "删除失败，系统出错");
        }

        return RespStat.build(200, "删除成功");
    }

    @Override
    public Role findById(int id) {
        //判断ID是否合法
        if (1 > id) {
            throw new RuntimeException("id非法");
        }

        //数据库查询数据
        Role role = roleMapper.selectByPrimaryKey(id);
        if (null == role) {
            throw new RuntimeException("系统出错，没找到该角色");
        }

        //查询该id角色下拥有的权限
        List<Permission> role_permissionList = roleMapper.findPermissionsById(id);
        role.setPermissionList(role_permissionList);

        return role;
    }

    @Override
    public RespStat modifyName(Role role) {
        //获取角色的ID和名称
        int id = role.getId();
        String name = role.getName();

        //角色ID是否合法
        if (1 > id) {
            return RespStat.build(600, "id非法", role);
        }

        //数据库中是否存在该ID的角色
        Role existRole = roleMapper.selectByPrimaryKey(id);
        if (null == existRole) {
            return RespStat.build(600, "没有该角色", role);
        }

        //数据库中该ID的角色名称是否和修改值相同
        if (existRole.getName().equals(name)) {
            return RespStat.build(600, "修改的和原先名称相同", role);
        }

        //数据库中其它ID的角色名称是否和修改的名称相同的存在
        RoleExample example = new RoleExample();
        example.createCriteria().andIdNotEqualTo(id).andNameEqualTo(name);
        List<Role> nameList = roleMapper.selectByExample(example);
        if (!nameList.isEmpty()) {
            return RespStat.build(600, "修改名称已存在", role);
        }

        int row = roleMapper.updateByPrimaryKeySelective(role);
        if (0 == row) {
            return RespStat.build(600, "系统出错，修改失败", role);
        }

        return RespStat.build(200, "修改成功", role);
    }

    @Override
    public RespStat modifyPermissions(int id, int[] permissions) {
        if (1 > id) {
            return RespStat.build(500, "id非法", id);
        }

        if (null == permissions) {
            //权限为空，删除该ID的所有权限
            roleMapper.deletePermissionById(id);
        } else {
            //更新该ID的所有权限
            roleMapper.updatePermissionById(id, permissions);
        }

        return RespStat.build(200, "角色权限修改成功");
    }
}
