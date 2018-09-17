package com.sawcao.ssradmin.admin.init;

import com.sawcao.ssradmin.admin.constant.SSHConstant;
import com.sawcao.ssradmin.admin.service.VpsService;
import com.sawcao.ssradmin.admin.utils.SSHUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: caorui
 * Time: 2018/9/17
 **/
@Slf4j
@Component
public class SSHInitLineRunner implements CommandLineRunner {
    @Autowired
    VpsService vpsService;
    @Autowired
    SSHConstant sshConstant;

    @Override
    public void run(String... var1) throws Exception{
        log.info("初始化开始---------------");
        log.info("SSH初始化开始------------");
        initSSHConnection();
        log.info("SSH初始化结束------------");
        log.info("初始化结束---------------");
    }

    private void initSSHConnection(){
        List<SSHUtil> sshUtils = new ArrayList<>();
        vpsService.getAllVps().forEach(e ->{
            SSHUtil sshconnect = new SSHUtil(e.getIp(),e.getVpsName(),e.getUserName(),e.getPassword());
            sshUtils.add(sshconnect);
        });
        sshConstant.setSshUtils(sshUtils);
    }
}
