package com.yangqihang.service;

import com.github.pagehelper.PageInfo;
import com.yangqihang.RespStat;
import com.yangqihang.entity.Role;

import java.util.List;

public interface IRoleService {
    PageInfo<Role> findAll(int pageNum, int pageSize);

    List<Role> findAll();

    RespStat add(Role role);

    RespStat deleteById(int id);

    Role findById(int id);

    RespStat modifyName(Role role);

    RespStat modifyPermissions(int id, int[] permissions);
}
