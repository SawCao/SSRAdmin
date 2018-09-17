package com.sawcao.ssradmin.admin.constant;

import com.sawcao.ssradmin.admin.dto.VPS;
import com.sawcao.ssradmin.admin.utils.SSHUtil;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: caorui
 * Time: 2018/8/31
 **/
@Data
@Component
public class SSHConstant {
    public static String CMDString = "cd libsodium-1.0.16\n" + "cd shadowsocksR-b\n";
    public List<SSHUtil> sshUtils;
}
