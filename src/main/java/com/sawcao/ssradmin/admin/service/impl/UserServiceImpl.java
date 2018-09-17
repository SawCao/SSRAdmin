package com.sawcao.ssradmin.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.sawcao.ssradmin.admin.constant.SSHConstant;
import com.sawcao.ssradmin.admin.dto.User;
import com.sawcao.ssradmin.admin.dto.VPS;
import com.sawcao.ssradmin.admin.mapper.UserMapper;
import com.sawcao.ssradmin.admin.mapper.VpsMapper;
import com.sawcao.ssradmin.admin.service.UserService;
import com.sawcao.ssradmin.admin.service.VpsService;
import com.sawcao.ssradmin.admin.utils.JsonUtil;
import com.sawcao.ssradmin.admin.utils.SSHUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: caorui
 * Time: 2018/9/5
 **/
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private VpsMapper vpsMapper;
    @Autowired
    private VpsService vpsService;
    @Autowired
    private SSHConstant sshConstant;

    @Override
    public User getUser(String userName){
        return userMapper.findByCondition("userName",userName);
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

    @Override
    public void deleteUser(String userName){
        userMapper.deleteUser(userName);
    }

    @Override
    public void deleteAllUser(){userMapper.deleteAll();}

    @Override
    public void refreshUser(String vpsName){
        sshConstant.getSshUtils().forEach(e->{
            if(e.getVpsName().equals(vpsName)){
                e.execute("pwd");
                String s = e.execute("cat mudb.json");
                ArrayList<JSONObject> jsonObjects = JSON.parseObject(s, new TypeReference<ArrayList<JSONObject>>(){});
                jsonObjects.forEach(ee -> addUser(new User(ee.getString("user"),
                        vpsName,
                        ee.getString("passwd"),
                        ee.getString("port"),
                        ee.getInteger("transfer_enable").toString(),
                        ee.getInteger("d").toString(),
                        ee.getInteger("u").toString(),
                        ee.getInteger("speed_limit_per_con").toString(),
                        ee.getInteger("speed_limit_per_user").toString(),
                        ee.getString("protocol_param"),
                        ee.getInteger("month").toString()))
                );
                log.info("成功同步！！！！");
            }
        });

    }

}
