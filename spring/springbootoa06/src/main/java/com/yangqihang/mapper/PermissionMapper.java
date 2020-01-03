package com.yangqihang.mapper;

import com.yangqihang.entity.Permission;
import com.yangqihang.entity.PermissionExample;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * PermissionMapper继承基类
 */
@Mapper
@Repository
public interface PermissionMapper extends MyBatisBaseDao<Permission, Integer, PermissionExample> {
}