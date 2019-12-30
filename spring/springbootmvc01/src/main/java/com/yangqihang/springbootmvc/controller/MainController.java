package com.yangqihang.springbootmvc.controller;

import com.yangqihang.springbootmvc.entity.City;
import com.yangqihang.springbootmvc.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/city")
public class MainController {

    @Autowired
    private MainService srv;

    @RequestMapping("/list")
    public String list(ModelMap map) {
        List<City> list = srv.findAll();

        map.addAttribute("list",list);

        return "list";
    }
}
