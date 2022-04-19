package com.ye.service.impl;

import com.ye.mapper.UserMapper;
import com.ye.pojo.User;
import com.ye.service.UserService;
import com.ye.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.Map;

public class UserServiceImpl implements UserService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public User select(String userName,String password){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user=mapper.select(userName,password);
        sqlSession.close();
        return user;
    }


    @Override
    public User selectByName(String userName){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.selectByUserNameUser(userName);
        sqlSession.close();
        return user;

    }

    @Override
    public void add(User user){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.add(user);
        sqlSession.commit();

    }

    @Override
    public User selectById(int id){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.selectById(id);
        sqlSession.close();
        return user;

    }

    public User selectMap(Map map){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.selectMap(map);
        sqlSession.close();
        return user;
    }

}
