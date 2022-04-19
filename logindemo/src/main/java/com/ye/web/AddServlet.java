package com.ye.web;

import com.alibaba.fastjson.JSON;
import com.ye.pojo.Brand;
import com.ye.pojo.User;
import com.ye.service.BrandService;

import com.ye.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "AddServlet", value = "/addServlet")
public class AddServlet extends HttpServlet {
    private BrandService service =new BrandServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Axios提交的数据是JSON格式，要用getReader方法读取。
        BufferedReader br = request.getReader();
        //读取JSON格式的字符串
        String params = br.readLine();
        //JSON格式字符串转换为Brand对象
        Brand brand = JSON.parseObject(params, Brand.class);
        //获取当前session
        HttpSession session = request.getSession();
        //获取当前登陆的用户
        User user = (User) session.getAttribute("user");
        //将
        brand.setUser(user.getId());
        try {
            service.addBrand(brand);
            response.getWriter().write("success");
        } catch (Exception e) {
            response.getWriter().write("fail");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
