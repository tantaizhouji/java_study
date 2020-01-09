package com.yangqihang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    /**
     * 跳转到主页
     *
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    /**
     * 跳转到无权限报错页面
     * @return
     */
    @RequestMapping("/errorPage")
    public String errorPage(Model model) {
        return "errorPage";
    }
}
