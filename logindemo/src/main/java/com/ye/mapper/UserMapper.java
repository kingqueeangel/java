package com.ye.mapper;

import com.ye.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface UserMapper {

    User select(@Param("userName") String userName, @Param("password") String password);
    User selectByUserNameUser(@Param("userName")String userName);
    void add(User user);
    User selectById(@Param("id")int id);
    User selectMap(Map map);
}
