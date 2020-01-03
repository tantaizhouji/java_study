package com.yangqihang.mapper;

import com.yangqihang.entity.Permission;
import com.yangqihang.entity.Role;
import com.yangqihang.entity.RoleExample;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * RoleMapper继承基类
 */
@Mapper
@Repository
public interface RoleMapper extends MyBatisBaseDao<Role, Integer, RoleExample> {

    //添加权限
    void updatePermissionById(int id, int[] permissions);

    //查询角色的权限列表
    List<Permission> findPermissionsById(int id);

    void deletePermissionById(int id);
}