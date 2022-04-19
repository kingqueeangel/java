package com.ye.json;

import com.alibaba.fastjson.JSON;
import com.ye.pojo.Brand;
import com.ye.pojo.User;
import com.ye.service.BrandService;

import com.ye.service.impl.BrandServiceImpl;
import com.ye.service.impl.UserServiceImpl;

import java.beans.Encoder;
import java.util.Base64;
import java.util.List;

public class FastJsonDemo {

    public static void main(String[] args) {
        BrandService brandService =new BrandServiceImpl();
        UserServiceImpl userService = new UserServiceImpl();
        User user =new User();
        user.setId(2);
        user.setUserName("叶");
        user.setPassword("daf");
        String s = JSON.toJSONString(user);
        //System.out.println(s);
        User user1 = JSON.parseObject(s, User.class);
       // System.out.println("user="+user1);
        List<Brand> brands = brandService.selectAll(3);

        System.out.println(brands);
        String s1 = JSON.toJSONString(brands);
        System.out.println(s1);
    }
}
