package com.sawcao.ssradmin.controller;


import com.sawcao.ssradmin.domain.User;
import com.sawcao.ssradmin.dto.VPS;
import com.sawcao.ssradmin.service.UserService;
import com.sawcao.ssradmin.utils.SSHUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * Created by IntelliJ IDEA.
 * User: caorui
 * Time: 2018/8/31
 **/
@RestController
@RequestMapping(value = "/users")
public class MainControll {

    @Autowired
    UserService userService;

    @GetMapping
    public String getUserList(ModelMap map) {
        map.addAttribute("userList", userService.findAllUser());
        return "userList";
    }

    /**
     * 显示创建用户表单
     *
     */
    @GetMapping(value = "/adduserform")
    public String createUserForm(ModelMap map) {
        map.addAttribute("user", new User());
        map.addAttribute("action", "create");
        return "userForm";
    }

    @PostMapping("/adduser")
    public String testSSH(@ModelAttribute User user){
        VPS vps = userService.getVps(user.getVpsName());
        SSHUtil sshconnect = new SSHUtil(vps.getIp(),vps.getUserName(),vps.getPassword());
        return sshconnect.execute("cd libsodium-1.0.16\n" +
                "cd shadowsocksR-b\n" +
                "python mujson_mgr.py " +
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
                " -M " + user.getMonth());

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
