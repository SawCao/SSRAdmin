package com.sawcao.ssradmin.admin.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: caorui
 * Time: 2018/8/31
 **/
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userName;
    private String vpsName;
    private String password;
    private String port;
    private String transfer;
    private String dtrans;
    private String utrans;
    private String speedThread;
    private String speedPort;
    private String userNum;
    private String months;

    public User(String userName, String vpsName, String month, String port) {
        this.userName = userName;
        this.vpsName = vpsName;
        this.months = month;
        this.port = port;
        this.transfer = "40";
        this.speedThread = "1500";
        this.speedPort = "2000";
        this.userNum = "3";
        this.dtrans = "0";
        this.utrans = "0";
    }
    public User(){}
}
