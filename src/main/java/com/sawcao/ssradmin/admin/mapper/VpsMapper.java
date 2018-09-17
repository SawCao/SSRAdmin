package com.sawcao.ssradmin.admin.mapper;

import com.sawcao.ssradmin.admin.dto.VPS;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: caorui
 * Time: 2018/9/5
 **/
@Mapper
public interface VpsMapper {
    @Select("SELECT * FROM vps")
    List<VPS> findAllVps();

    @Insert("INSERT INTO vps (vpsService,vpsName,ip,userName,password) VALUES (#{vpsService},#{vpsName},#{ip},#{userName},#{password})")
    void addVps(VPS vps);

    @Select("SELECT * FROM vps WHERE vpsName = #{vpsName}")
    VPS findVps(String vpsName);

    @Delete("DELETE FROM vps WHERE vpsName = #{vpsName}")
    void deleteVps(String vpsName);
}
