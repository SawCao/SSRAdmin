package com.sawcao.ssradmin.admin.mapper;

import com.sawcao.ssradmin.admin.dto.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: caorui
 * Time: 2018/9/5
 **/
@Mapper
public interface UserMapper {
    @Options(useGeneratedKeys = true, keyProperty = "id") //回写自增的主键ID
    @Insert("INSERT INTO users (userName,vpsName,port,months,transfer,speedPort,speedThread,userNum) VALUES (#{userName},#{vpsName},#{port},#{months},#{transfer},#{speedPort},#{speedThread},#{userNum})")
    Integer addUser(User user);

    @Delete("DELETE FROM users WHERE username = #{userName}")
    Integer deleteUser(String userName);

    @Select("SELECT * FROM users")
    List<User> findAllUser();

    @Update("UPDATE users SET #{willUpdate} = #{updateContent} WHERE username = #{userName}")
    Integer updateUser(String willUpdate, String userName, String updateContent);

    @Select("SELECT * FROM users WHERE #{willCondition} = #{condition}")
    User findByCondition(@Param("willCondition") String willCondition, @Param("condition") String condition);

    @Delete("DELETE FROM users")
    void deleteAll();
}
