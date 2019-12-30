package com.yangqihang.spring.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * ORM映射
 * 线程安全，成员属性不能被共享
 */

@Component
@Scope("prototype")
public class User {

    @Value("zhangfg")
    private String loginName;
    @Value("123456")
    private String password;
    @Autowired
    private Pet pet;

    public User() {
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
