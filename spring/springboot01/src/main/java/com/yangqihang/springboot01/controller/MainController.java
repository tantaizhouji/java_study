package com.yangqihang.springboot01.controller;

import com.yangqihang.springboot01.entity.City;
import com.yangqihang.springboot01.service.ServiceMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 在我们访问 http://主机名:端口号/context-path/Controller的URI/方法的URI
 * http://localhost:8080/boot/user/list
 */

@Controller
@RequestMapping("/user")
public class MainController {

    @Autowired
    private ServiceMain srv;

    @RequestMapping("/list")
    public String list(ModelMap map) {
        List<City> list = srv.findList();

        map.addAttribute("list", list);

        return "list";
    }

    @RequestMapping("/add")
    public String add(@ModelAttribute City city, ModelMap map) {
        if (null != city.getId()) {
            System.out.println("================");
            System.out.println("收到添加指令");
            String str = srv.add(city);
            map.addAttribute("addResult", str);
        }

        return "add";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        System.out.println("================");
        System.out.println("搜到删除指令");
        srv.deleteById(id);

        return "redirect:/user/list";
    }

}
