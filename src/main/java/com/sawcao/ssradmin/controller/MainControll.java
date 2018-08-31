package com.sawcao.ssradmin.controller;


import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import com.sawcao.ssradmin.constant.SSHConstant;
import com.sawcao.ssradmin.domain.User;
import com.sawcao.ssradmin.dto.VPS;
import com.sawcao.ssradmin.utils.JsonUtil;
import com.sawcao.ssradmin.utils.SSHUtil;
import org.python.bouncycastle.jce.exception.ExtIOException;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import java.io.*;
import java.util.concurrent.ExecutionException;

/**
 * Created by IntelliJ IDEA.
 * User: caorui
 * Time: 2018/8/31
 **/
@RestController
public class MainControll {

    @PostMapping("/testssh")
    public String testSSH(@RequestHeader String vpsName,
                          @RequestBody User user){
        VPS vps = JsonUtil.getVps(vpsName);
        SSHUtil sshconnect = new SSHUtil(vps.getIp(),vps.getUserName(),vps.getPassword());
        return sshconnect.execute("cd libsodium-1.0.16\ncd shadowsocksR-b\n" +
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

}
