package com.ye.test;

import com.ye.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {


    @Test
    public void springTest(){

        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");

        UserService userDao = (UserService)app.getBean("userDao");
        UserService userDao1 = (UserService)app.getBean("userDao");
        System.out.println(userDao);
        System.out.println(userDao1);


    }


}
