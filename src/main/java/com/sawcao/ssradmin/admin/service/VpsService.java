package com.sawcao.ssradmin.admin.service;

import com.sawcao.ssradmin.admin.dto.VPS;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: caorui
 * Time: 2018/9/14
 **/
public interface VpsService {
    List<VPS> getAllVps();

    void setVps(VPS vps);

    VPS getVps(String userName);
}
