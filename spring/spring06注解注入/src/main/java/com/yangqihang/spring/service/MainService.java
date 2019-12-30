package com.yangqihang.spring.service;

import com.yangqihang.spring.dao.UserDao;
import com.yangqihang.spring.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 处理具体业务逻辑
 * 比如,校验账号和密码
 */

// @Component 注册bean 相当于<bean id=""

@Component
public class MainService {

    @Autowired
    UserDao dao;

    public String login(String loginName, String password) {
        if (null == loginName || null == password) {
            throw new RuntimeException("用户名或密码不能为空");
        }

        User user = dao.getUserByName(loginName);
        if (null == user) {
            throw new RuntimeException("该用户不存在");
        }

        if (!password.equals(user.getPassword())) {
            return "密码错误，登录失败";
        }

        return "登录成功";
    }
}
