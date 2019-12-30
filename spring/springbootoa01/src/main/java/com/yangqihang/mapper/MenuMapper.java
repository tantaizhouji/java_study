package com.yangqihang.mapper;

import com.yangqihang.entity.Menu;
import com.yangqihang.entity.MenuExample;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * MenuMapper继承基类
 */
@Mapper
@Repository
public interface MenuMapper extends MyBatisBaseDao<Menu, Integer, MenuExample> {
}