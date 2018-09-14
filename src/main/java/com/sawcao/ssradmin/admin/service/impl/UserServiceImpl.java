package com.sawcao.ssradmin.admin.service.impl;

import com.sawcao.ssradmin.admin.dto.User;
import com.sawcao.ssradmin.admin.dto.VPS;
import com.sawcao.ssradmin.admin.mapper.UserMapper;
import com.sawcao.ssradmin.admin.mapper.VpsMapper;
import com.sawcao.ssradmin.admin.service.UserService;
import com.sawcao.ssradmin.admin.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: caorui
 * Time: 2018/9/5
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private VpsMapper vpsMapper;


    @Override
    public String getUser(String userName) throws IOException {
        return JsonUtil.getUser(userName);
    }

    @Override
    public void addUser(User user) {
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

    @Override
    public User getUserByCondition(String conditionName, String condition) {
        return userMapper.findByCondition(conditionName, condition);
    }
}
