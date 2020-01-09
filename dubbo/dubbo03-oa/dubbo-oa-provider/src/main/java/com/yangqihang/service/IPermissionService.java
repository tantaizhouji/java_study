package com.yangqihang.service;

import com.github.pagehelper.PageInfo;
import com.yangqihang.RespStat;
import com.yangqihang.entity.Permission;

import java.util.List;

public interface IPermissionService {
    PageInfo<Permission> findAll(int pageNum, int pageSize);

    List<Permission> findAll();

    RespStat add(Permission permission);

    RespStat deleteById(int id);

    Permission findById(int id);

    RespStat modify(Permission permission);
}
