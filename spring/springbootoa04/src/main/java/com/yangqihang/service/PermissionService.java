package com.yangqihang.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yangqihang.RespStat;
import com.yangqihang.entity.Permission;
import com.yangqihang.entity.PermissionExample;
import com.yangqihang.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {

    @Autowired
    private PermissionMapper perMapper;

    public PageInfo<Permission> findAll(int pageNum, int pageSize) {

        PageHelper.startPage(pageNum, pageSize);

        PermissionExample example = new PermissionExample();
        List<Permission> perList = perMapper.selectByExample(example);
        PageInfo<Permission> perPageInfo = new PageInfo<>(perList, 5);

        return perPageInfo;
    }

    public RespStat add(Permission permission) {
        String name = permission.getName();
        String uri = permission.getUri();
        if ("".equals(name) || "".equals(uri) || null == name || null == uri) {
            return RespStat.build(500, "名称或uri不能为空");
        }

        PermissionExample exampleName = new PermissionExample();
        PermissionExample exampleURI = new PermissionExample();
        exampleName.createCriteria().andNameEqualTo(name);
        exampleURI.createCriteria().andNameEqualTo(uri);
        List<Permission> exampleNameList = perMapper.selectByExample(exampleName);
        List<Permission> exampleURIList = perMapper.selectByExample(exampleURI);
        if (!exampleNameList.isEmpty()) {
            return RespStat.build(500, "已有相同名称");
        }
        if (!exampleURIList.isEmpty()) {
            return RespStat.build(500, "已有相同uri");
        }

        int row = perMapper.insertSelective(permission);

        if (0 == row) {
            return RespStat.build(500, "系统出错，添加失败");
        }

        return RespStat.build(200, "权限添加成功,3秒后返回", permission);
    }

    public RespStat deleteById(int id) {
        if (id < 1) {
            return RespStat.build(600, "非法的id");
        }

        int row = perMapper.deleteByPrimaryKey(id);

        if (0 == row) {
            return RespStat.build(600, "系统出错，删除失败");
        }

        return RespStat.build(200, "删除成功");
    }

    public Permission findById(int id) {
        if (id < 1) {
            throw new RuntimeException("id非法，系统出错");
        }

        Permission permission = perMapper.selectByPrimaryKey(id);

        if (null == permission) {
            throw new RuntimeException("无法查询到该id存在");
        }

        return permission;
    }

    public RespStat modify(Permission permission) {
        Integer id = permission.getId();
        String name = permission.getName();
        String uri = permission.getUri();
        System.out.println(uri);
        Boolean c = permission.getC();
        Boolean r = permission.getR();
        Boolean u = permission.getU();
        Boolean d = permission.getD();

        //判断id是否合法
        if (1 > id) {
            return RespStat.build(400, "id非法,系统出错", permission);
        }

        //判断数据库中是否存在该id的数据
        Permission exist = perMapper.selectByPrimaryKey(id);
        if (null == exist) {
            return RespStat.build(400, "无法修改不存在id的权限", permission);
        }

        //判断数据库中的数据是否和要修改数据的信息一致
        if (exist.getName().equals(name) && exist.getUri().equals(uri) &&
                exist.getC() == c && exist.getR() == r && exist.getU() == u && exist.getD() == d) {
            return RespStat.build(400, "原来与修改之后相同，不用修改", permission);
        }

        //数据库中其它ID的数据是否有相同的名称
        PermissionExample exampleName = new PermissionExample();
        exampleName.createCriteria().andIdNotEqualTo(id).andNameEqualTo(name);
        List<Permission> existNames = perMapper.selectByExample(exampleName);
        if (!existNames.isEmpty()) {
            return RespStat.build(400, "修改的权限名称已存在，无法修改", permission);
        }

        //数据库中其它ID的数据是否有相同的uri
        PermissionExample exampleURI = new PermissionExample();
        exampleURI.createCriteria().andIdNotEqualTo(id).andUriEqualTo(uri);
        List<Permission> existURIs = perMapper.selectByExample(exampleURI);
        if (!existURIs.isEmpty()) {
            return RespStat.build(400, "修改的uri已存在，无法修改", permission);
        }

        //取出修改数据库中的行数
        int row = perMapper.updateByPrimaryKeySelective(permission);

        //修改行数为0
        if (0 == row) {
            return RespStat.build(400, "系统出错，修改失败", permission);
        }

        return RespStat.build(200, "修改权限成功，3秒后返回", permission);
    }
}
