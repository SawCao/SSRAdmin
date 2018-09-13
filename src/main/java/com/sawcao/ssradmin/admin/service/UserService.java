package com.sawcao.ssradmin.admin.service;

import com.sawcao.ssradmin.admin.domain.User;
import com.sawcao.ssradmin.admin.dto.VPS;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: caorui
 * Time: 2018/9/5
 **/

public interface UserService {

    /**
     * @author caorui
     * @param [vpsName]
     * @return com.sawcao.ssradmin.dto.VPS
     */
    VPS getVps(String vpsName);

    /**
     * @author caorui
     * @param [vpsName, ip, username, password]
     * @return void
     */
    void setVps(String vpsName, String ip, String username, String password);

    /**
     * @author caorui
     * @param [userName]
     * @return java.lang.String
     */
    String getUser(String userName) throws IOException;

    /**
     * @author caorui
     * @param [userName, vpsName, month, port]
     * @return void
     */
    void addUser(User user);

    /**
     * @author caorui
     * @param [userName, month]
     * @return void
     */
    void upadateUserMonth(String userName, String month);

    /**
     * @author caorui
     * @param []
     * @return java.util.List<com.sawcao.ssradmin.domain.User>
     */
    List<User> findAllUser();

    /**
     * @author caorui
     * @param [willFindByCondition]
     * @return com.sawcao.ssradmin.admin.domain.User
     */
    User findMonthsByCondition(String willFindByCondition);
}
