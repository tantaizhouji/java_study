package com.yangqihang.mapper;

import com.yangqihang.entity.Account;
import com.yangqihang.entity.AccountExample;
import com.yangqihang.entity.Permission;
import com.yangqihang.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * AccountMapper继承基类
 */
@Mapper
@Repository
public interface AccountMapper extends MyBatisBaseDao<Account, Integer, AccountExample> {
    List<Account> selectByPermission();

    //根据用户的ID查询用户的角色
    List<Role> findRoleById(Integer id);

    //根据用户的ID查询用户的权限
    List<Permission> findPermissionById(Integer id);

    //根据用户ID删除用户的角色
    void deleteRolesById(int id);

    //根据用户的ID更新用户的角色
    void updateRolesById(int[] roles, int id);
}