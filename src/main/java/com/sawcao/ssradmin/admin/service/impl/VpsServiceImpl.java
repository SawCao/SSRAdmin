package com.sawcao.ssradmin.admin.service.impl;

import com.sawcao.ssradmin.admin.dto.VPS;
import com.sawcao.ssradmin.admin.mapper.VpsMapper;
import com.sawcao.ssradmin.admin.service.VpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: caorui
 * Time: 2018/9/14
 **/
@Service
public class VpsServiceImpl implements VpsService {
    @Autowired
    private VpsMapper vpsMapper;

    @Override
    public List<VPS> getAllVps(){
        return vpsMapper.findAllVps();
    }

    @Override
    public void setVps(VPS vps){
        vpsMapper.addVps(vps);
    }

    @Override
    public VPS getVps(String userName){return vpsMapper.findVps(userName);}
}
