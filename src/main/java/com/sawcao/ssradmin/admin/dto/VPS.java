package com.sawcao.ssradmin.admin.dto;

import lombok.Data;


/**
 * Created by IntelliJ IDEA.
 * User: caorui
 * Time: 2018/8/31
 **/
@Data
public class VPS {
    private static final long serialVersionUID = 1L;

    private String vpsService;
    private String vpsName;
    private String ip;
    private String userName;
    private String password;

}
