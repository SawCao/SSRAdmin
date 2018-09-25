package com.sawcao.ssradmin.admin.service;

import com.sawcao.ssradmin.admin.dto.User;
import com.sawcao.ssradmin.admin.dto.VPS;

import java.io.IOException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: caorui
 * Time: 2018/9/5
 **/

public interface UserService {


    /**
     * @param [userName]
     * @return java.lang.String
     * @author caorui
     */
    User getUser(String userName) throws IOException;

    /**
     * @param [userName, vpsName, month, port]
     * @return void
     * @author caorui
     */
    void addUser(User user);

    /**
     * @param [userName, month]
     * @return void
     * @author caorui
     */
    void upadateUserMonth(String userName, String month);

    /**
     * @param []
     * @return java.util.List<com.sawcao.ssradmin.domain.User>
     * @author caorui
     */
    List<User> findAllUser();

    /**
     * @param [willFindByCondition]
     * @return com.sawcao.ssradmin.admin.domain.User
     * @author caorui
     */
    User getUserByCondition(String willFindByCondition);

    /**
     * @author caorui
     * @param [userName]
     * @return void
     */
    void deleteUser(String userName);

    void deleteAllUser();

    void refreshUser(String vpsName);

}
