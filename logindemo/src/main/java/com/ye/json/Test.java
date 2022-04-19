package com.ye.json;

import com.ye.pojo.User;
import com.ye.service.impl.UserServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {

        UserServiceImpl userService = new UserServiceImpl();


        String name = "aaa660";
        String password ="lina860402";
        Map map =new HashMap<>();
        User user =new User();
        user.setUserName(name);
        user.setPassword(password);
        map.put("user",user);
        User user1 = userService.selectMap(map);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(user1);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
}
