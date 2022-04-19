package com.ye.service;

import com.ye.mapper.UserMapper;
import com.ye.pojo.User;
import org.apache.ibatis.session.SqlSession;

import java.util.Map;

public interface UserService {

    //定义接口，创建泛型对象
    User select(String userName, String password);
    User selectByName(String userName);
    void add(User user);
    User selectById(int id);
    User selectMap(Map map);
}
