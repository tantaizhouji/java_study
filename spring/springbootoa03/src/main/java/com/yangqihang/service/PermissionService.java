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

        return RespStat.build(200, "添加成功", permission);
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
}
