package com.sawcao.ssradmin.admin.controller;


import com.sawcao.ssradmin.admin.domain.User;
import com.sawcao.ssradmin.admin.dto.VPS;
import com.sawcao.ssradmin.admin.service.UserService;
import com.sawcao.ssradmin.admin.utils.SSHUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by IntelliJ IDEA.
 * User: caorui
 * Time: 2018/8/31
 **/
@Controller
@RequestMapping("/user")
public class MainControll {

    private static String CMDString = "cd libsodium-1.0.16\n" + "cd shadowsocksR-b\n" + "python mujson_mgr.py ";

    @Autowired
    public UserService userService;

    @GetMapping("/userlist")
    public String getUserList(ModelMap map) {
        map.addAttribute("userList", userService.findAllUser());
        return "UserList";
    }

    /**
     * 显示用户信息表单，可修改及新增
     *
     */
    @GetMapping(value = "/create")
    public String createUserForm(ModelMap map) {
        //map.addAttribute("user", new User());
        map.addAttribute("user",new User());
        map.addAttribute("action", "create");
        return "userForm";
    }

    @PostMapping(value = "/create")
    public String createUser(@ModelAttribute User user) {
        userService.addUser(user);
        return "redirect:/user/userlist";
    }

    @GetMapping(value = "/update/{id}")
    public String addUserForm(@PathVariable String id, ModelMap map) {
        //map.addAttribute("user", new User());
        map.addAttribute("user",userService.findMonthsByCondition(id));
        map.addAttribute("action", "update");
        return "userForm";
    }

    @PostMapping(value = "/update")
    public String addUser(@ModelAttribute User user) {
        //map.addAttribute("user", new User());
        userService.upadateUserMonth(user.getUserName(),user.getMonths());
        /*VPS vps = userService.getVps(user.getVpsName());
        SSHUtil sshconnect = new SSHUtil(vps.getIp(),vps.getUserName(),vps.getPassword());
        sshconnect.execute("cd libsodium-1.0.16\n" +
                "cd shadowsocksR-b\n" +
                "python mujson_mgr.py " +
                "-e -u" + user.getUserName() +
                " -M" + user.getMonths());*/
        return "UserList";
    }

    @PostMapping("/adduser")
    public String testSSH(@ModelAttribute User user){

        VPS vps = userService.getVps(user.getVpsName());
        SSHUtil sshconnect = new SSHUtil(vps.getIp(),vps.getUserName(),vps.getPassword());
        return sshconnect.execute(CMDString +
                "-a -u " + user.getUserName() +
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

    @GetMapping("/updatemonth")
    public void updateMonth(@RequestHeader String username,
                            @RequestHeader String month){
        try {
            String vpsName = userService.getUser(username);
            if(vpsName == null)
                throw new Exception("sadasd");
            else {
                userService.upadateUserMonth(vpsName, month);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
