package com.sawcao.ssradmin.mapper;

import com.sawcao.ssradmin.dto.VPS;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


/**
 * Created by IntelliJ IDEA.
 * User: caorui
 * Time: 2018/9/5
 **/
@Mapper
public interface VpsMapper {
    @Select("SELECT * FROM vps WHERE vpsName = #{vpsName}")
    VPS findVps(String vpsName);

    @Insert("INSERT INTO vps (vpsName,ip,userName,password) VALUES (#{vpsName},#{ip},#{userName},#{password})")
    void addVps(String vpsName, String ip, String userName, String password);
}
