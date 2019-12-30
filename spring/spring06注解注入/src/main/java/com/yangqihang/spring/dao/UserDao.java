package com.yangqihang.spring.dao;

import com.yangqihang.spring.entity.User;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDao {

    @Autowired
    User user;

    public User getUserByName(String loginName) {
        if(loginName.equals(user.getLoginName())) {
            System.out.println(ToStringBuilder.reflectionToString(user));
            return user;
        }

        return null;
    }
}
