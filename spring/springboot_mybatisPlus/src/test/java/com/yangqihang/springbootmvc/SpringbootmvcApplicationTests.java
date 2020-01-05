package com.yangqihang.springbootmvc;

import com.yangqihang.springbootmvc.entity.City;
import com.yangqihang.springbootmvc.mapper.CityMapper;
import com.yangqihang.springbootmvc.service.CityServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringbootmvcApplicationTests {

    @Autowired
    private CityMapper cityMapper;

    @Autowired
    private CityServiceImpl cityService;

    @Test
    void contextLoads() {
    }

    /**
     * 测试mybatisPlus的mapper接口
     */
    @Test
    void testMapper() {
        System.out.println("----mapper----");
        List<City> cityList = cityMapper.selectList(null);
        System.out.println(cityList.get(0));
    }

    /**
     * 测试mybatisPlus的service
     */
    @Test
    void testService() {
        System.out.println("----service----");
        System.out.println(cityService.count());
    }
}
