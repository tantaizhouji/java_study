package com.yangqihang.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yangqihang.entity.Role;
import com.yangqihang.entity.RoleExample;
import com.yangqihang.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;

    public PageInfo<Role> findAll(int pageNum, int pageSize) {

        PageHelper.startPage(pageNum, pageSize);

        RoleExample example = new RoleExample();
        List<Role> roleList = roleMapper.selectByExample(example);
        PageInfo<Role> rolePageInfo = new PageInfo<>(roleList, 5);

        return rolePageInfo;
    }
}
