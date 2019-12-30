package com.yangqihang.service;

import com.github.pagehelper.PageHelper;
import com.yangqihang.entity.Menu;
import com.yangqihang.entity.MenuExample;
import com.yangqihang.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;

    public List<Menu> findAll() {

        MenuExample example = new MenuExample();

        List<Menu> menuList = menuMapper.selectByExample(example);

        return menuList;
    }

    public void add() {
        Menu menu = new Menu();
        menu.setName("首页");
        menu.setRoles("all");
        menu.setIndex("0");

        menuMapper.insert(menu);
    }

    public List<Menu> findByPage(Integer pageNum, Integer pageSize) {

        if (null != pageNum || null != pageSize) {
            PageHelper.startPage(pageNum, pageSize);
        }

        MenuExample example = new MenuExample();
        List<Menu> menuList = menuMapper.selectByExample(example);

        return menuList;
    }
}
