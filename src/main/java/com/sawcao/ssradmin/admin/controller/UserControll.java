package com.sawcao.ssradmin.admin.controller;


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

import java.io.IOException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: caorui
 * Time: 2018/8/31
 **/
@Controller
@RequestMapping("/user")
public class UserControll {

    private static String CMDString = "python mujson_mgr.py ";

    @Autowired
    public UserService userService;

    @Autowired
    private VpsService vpsService;

    @Autowired
    private SSHConstant sshConstant;

    //用户列表
    @GetMapping("/userlist")
    public String getUserList(ModelMap map) {
        map.addAttribute("userList", userService.findAllUser());
        return "UserList";
    }

    /**
     * 显示用户信息表单，可修改及新增
     */
    @GetMapping(value = "/create")
    public String createUserForm(ModelMap map) {
        map.addAttribute("user",new User());
        map.addAttribute("action", "create");
        return "userForm";
    }

    @PostMapping(value = "/create")
    public String createUser(@ModelAttribute User user) {
        userService.addUser(user);
        sshConstant.getSshUtils().forEach(e->{
            if(e.getVpsName().equals(user.getVpsName())){
                e.execute(SSHConstant.CMDString + CMDString + "-a -u " + user.getUserName() +
                        " -p " + user.getPort() +
                        " -k " + user.getPassword() +
                        " -m chacha20 " +
                        " -O auth_aes128_md5 " +
                        " -G " + user.getUserNum() +
                        " -o tls1.2_ticket_auth_compatible " +
                        " -s " + user.getSpeedThread() +
                        " -S " + user.getSpeedPort() +
                        " -t " + user.getTransfer() +
                        " -f \"25,465,233-266\" " +
                        " -M " + user.getMonths());
            }
        });
        return "redirect:/user/userlist";
    }

    //跳转用户更新页面
    @GetMapping(value = "/update/{id}")
    public String addUserForm(@PathVariable String id, ModelMap map) {
        map.addAttribute("user",userService.getUserByCondition("username",id));
        map.addAttribute("action", "update");
        return "userForm";
    }

    //用户更新接口
    @PostMapping(value = "/update")
    public String addUser(@ModelAttribute User user) {
        //map.addAttribute("user", new User());
        userService.upadateUserMonth(user.getUserName(),user.getMonths());
        sshConstant.getSshUtils().forEach(e->{
            if(e.getVpsName().equals(user.getVpsName())){
                e.execute(SSHConstant.CMDString +
                        "python mujson_mgr.py " +
                        "-e -u" + user.getUserName() +
                        " -M" + user.getMonths());
            }
        });
        return "UserList";
    }

    //删除用户
    @GetMapping(value = "/delete/{userId}")
    public String deleteUser(@PathVariable String userId) {
        User user = null;
        userService.deleteUser(userId);
        try {
            user = userService.getUser(userId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        User finalUser = user;
        sshConstant.getSshUtils().forEach(e->{
            if(e.getVpsName().equals(finalUser.getVpsName())){
                e.execute(SSHConstant.CMDString +
                        "python mujson_mgr.py " +
                        "-d -u" + finalUser.getUserName());
            }
        });
        return "redirect:/user/userlist";
    }

    //与各个服务器同步用户信息
    @GetMapping(value = "/refresh")
    public String refreshUser(){
        List<VPS> vpsList = vpsService.getAllVps();
        userService.deleteAllUser();
        vpsList.stream().forEach(e -> {
            userService.refreshUser(e.getVpsName());
        });
        return "redirect:/user/userlist";
    }

}
