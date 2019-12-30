package com.yangqihang.springbootmvc.service;

import com.yangqihang.springbootmvc.dao.MainDao;
import com.yangqihang.springbootmvc.entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainService {

    @Autowired
    private MainDao dao;

    public List<City> findAll() {
        List<City> list = dao.findAll();

        return list;
    }
}
