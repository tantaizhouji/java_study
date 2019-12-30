package com.tinygame.herostory.login.db;

import org.apache.ibatis.annotations.Param;

/**
 * 用户DAO
 */
public interface UserDao {

    /**
     * 根据用户名称获取用户
     *
     * @param userName
     * @return
     */
    UserEntity getUserByName(@Param("userName") String userName);

    /**
     * 添加用户实体
     *
     * @param newUserEntity 用户实体
     */
    void insertInto(UserEntity newUserEntity);
}
