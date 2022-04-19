package com.ye.web;

import com.alibaba.fastjson.JSON;
import com.ye.pojo.Brand;
import com.ye.pojo.User;
import com.ye.service.BrandService;
import com.ye.service.impl.BrandServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SelectAllServlet", value = "/selectAllServlet")
public class SelectAllServlet extends HttpServlet {
    //创建sql服务对象
    private BrandService service = new BrandServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取session对象
        HttpSession session = request.getSession();
        //获取当前用户
        User user = (User) session.getAttribute("user");
        //按当前用户查询brands对象
        List<Brand> brands = service.selectAll(user.getId());
        //将brands对象序列化为JSON对象
        String brandString = JSON.toJSONString(brands);
        //设置响应消息头编码格式utf-8
        response.setContentType("text/json;charset=utf-8");
        //返回响应数据JSON给前端
        response.getWriter().write(brandString);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
