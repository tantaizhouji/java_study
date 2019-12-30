package com.yangqihang.springboot01.service;

import com.yangqihang.springboot01.dao.DaoMain;
import com.yangqihang.springboot01.entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceMain {

    @Autowired
    private DaoMain dao;

    public List<City> findList() {
        System.out.println("================");
        System.out.println("正在查找列表");
        List list = dao.findList();

        return list;
    }

    public String add(City city) {
        if (("").equals(city.getName())) {
            return "无法添加名称为空的城市";
        }

        System.out.println("正在添加数据");
        String str = dao.add(city);

        return str;
    }

    public void deleteById(Integer id) {
        System.out.println("执行删除操作");
        dao.deleteById(id);
    }
}
