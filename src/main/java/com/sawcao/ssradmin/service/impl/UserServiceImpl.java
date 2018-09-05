package com.sawcao.ssradmin.service.impl;

import com.sawcao.ssradmin.domain.User;
import com.sawcao.ssradmin.dto.VPS;
import com.sawcao.ssradmin.mapper.UserMapper;
import com.sawcao.ssradmin.mapper.VpsMapper;
import com.sawcao.ssradmin.service.UserService;
import com.sawcao.ssradmin.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: caorui
 * Time: 2018/9/5
 **/
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private VpsMapper vpsMapper;

    @Override
    public VPS getVps(String vpsName) {
        return vpsMapper.findVps(vpsName);
    }

    @Override
    public void setVps(String vpsName, String ip, String username, String password) {
        vpsMapper.addVps(vpsName, ip, username, password);
    }

    @Override
    public String getUser(String userName) throws IOException {
        return JsonUtil.getUser(userName);
    }

    @Override
    public void addUser(String userName, String vpsName, String month, String port) {
        User user = new User(userName, vpsName, month, port);
        userMapper.addUser(user);
    }

    @Override
    public void upadateUserMonth(String userName, String month) {
        userMapper.updateUser("month", userName, month);
    }

    @Override
    public List<User> findAllUser() {
        return userMapper.findAllUser();
    }
}