package com.sawcao.ssradmin.admin.controller;

import com.sawcao.ssradmin.admin.dto.AdminUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 * User: caorui
 * Time: 2018/9/13
 **/
@Controller
@RequestMapping
public class MainControll {
    @GetMapping("login")
    public String Index(ModelMap map){
        map.addAttribute("adminUser",new AdminUser());
        return "login";
    }


    @PostMapping("login")
    public String verfiyAdmin(@ModelAttribute AdminUser adminUser, HttpServletRequest request){
        if(adminUser.getAdminName().equals("sawcao") && adminUser.getPassword().equals("110201021")) {
            request.getSession().setAttribute("adminuser",adminUser);
            return "redirect:/user/userlist";
        }
        return "login";
    }

}
