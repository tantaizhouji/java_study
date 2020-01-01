package com.yangqihang;

import com.yangqihang.entity.Account;
import com.yangqihang.mapper.AccountMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Springbootoa05ApplicationTests {
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
        List<Account> list =  accMapper.selectByPermission();
        System.out.println(list);
    }

}
