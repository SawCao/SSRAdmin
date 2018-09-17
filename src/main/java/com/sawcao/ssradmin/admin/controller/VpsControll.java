package com.sawcao.ssradmin.admin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.sawcao.ssradmin.admin.constant.SSHConstant;
import com.sawcao.ssradmin.admin.dto.User;
import com.sawcao.ssradmin.admin.dto.VPS;
import com.sawcao.ssradmin.admin.service.UserService;
import com.sawcao.ssradmin.admin.service.VpsService;
import com.sawcao.ssradmin.admin.utils.SSHUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: caorui
 * Time: 2018/9/14
 **/
@Controller
@RequestMapping("/vps")
public class VpsControll {

    @Autowired
    private VpsService vpsService;

    @Autowired
    private UserService userService;

    @Autowired
    private SSHConstant sshConstant;

    //列表
    @GetMapping("/vpslist")
    public String getUserList(ModelMap map) {
        map.addAttribute("vpsList", vpsService.getAllVps());
        return "vpslist";
    }

    /**
     * 显示VPS信息表单，可新增
     */
    @GetMapping(value = "/create")
    public String createVpsForm(ModelMap map) {
        //map.addAttribute("user", new User());
        map.addAttribute("vps",new VPS());
        map.addAttribute("action", "create");
        return "vpsform";
    }

    @GetMapping(value = "/delete/{vpsName}")
    public String deleteVps(@PathVariable String vpsName){
        vpsService.deleteVps(vpsName);
        return "redirect:/vps/vpslist";
    }


    @PostMapping(value = "/create")
    public String createUser(@ModelAttribute VPS vps) {
        vpsService.setVps(vps);
        return "redirect:/vps/vpslist";
    }

}
