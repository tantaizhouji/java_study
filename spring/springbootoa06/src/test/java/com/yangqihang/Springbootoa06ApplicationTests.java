package com.yangqihang;

import com.yangqihang.entity.Account;
import com.yangqihang.entity.Permission;
import com.yangqihang.mapper.AccountMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class Springbootoa06ApplicationTests {
    @Autowired
    AccountMapper accMapper;

    @Test
    void contextLoads() {
    }

    /**
     * 测试多表查询
     */
    @Test
    void selectByPermission() {
        List<Account> list = accMapper.selectByPermission();
        System.out.println(list);
    }

    /**
     * 测试空集合
     */
    @Test
    void hasAuth() {
        List<Permission> perList = new ArrayList<>();

        String uri = "/account/profile";
        for (Permission permission : perList) {
            if (uri.startsWith(permission.getUri())) {
                System.out.println("uri: " + uri);
                return;
            }
        }

        System.out.println("no");
    }
}
