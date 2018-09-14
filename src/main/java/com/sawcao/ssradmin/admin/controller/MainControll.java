package com.sawcao.ssradmin.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by IntelliJ IDEA.
 * User: caorui
 * Time: 2018/9/13
 **/
@Controller
@RequestMapping("/index")
public class MainControll {
    @GetMapping
    public String Index(ModelMap map){

        return "index";
    }
}
