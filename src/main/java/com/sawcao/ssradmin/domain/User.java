package com.sawcao.ssradmin.domain;

import lombok.Builder;
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
    private String password;
    private String port;
    private String transfer;
    private String speedThread;
    private String speedPort;
    private String userNum;
    private String month;

}
