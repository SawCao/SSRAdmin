package com.sawcao.ssradmin.admin.dto;

import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: caorui
 * Time: 2018/9/21
 **/
@Data
public class AdminUser {
    private String adminName;
    private String password;
    //可添加的用户数量
    private int addUserNum = 100;

    public AdminUser(String adminName, String password) {
        this.adminName = adminName;
        this.password = password;
    }

    public AdminUser() {

    }
}
