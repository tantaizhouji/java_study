package com.yangqihang.controller;

import org.springframework.stereotype.Controller;
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

}
