package com.tinygame.herostory.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户管理器
 */
public final class UserManager {

    /**
     * 用户字典
     */
    private static final Map<Integer, User> _userMap = new HashMap<>();

    /**
     * 私有化类默认构造器
     */
    private UserManager() {

    }

    /**
     * 添加用户
     *
     * @param newUser
     */
    public static void addUser(User newUser) {
        if (null != newUser) {
            _userMap.put(newUser.userId, newUser);
        }
    }

    /**
     * 根据用户Id移除用户
     *
     * @param userId
     */
    public static void removeUserById(int userId) {
        _userMap.remove(userId);
    }

    /**
     * 根据Id获取用户
     * @param userId
     * @return
     */
    public static User getUserById(int userId) {
        return _userMap.get(userId);
    }

    /**
     * 列表用户
     *
     * @return
     */
    public static Collection<User> listUser() {
        return _userMap.values();
    }

}
