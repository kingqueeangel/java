package com.ye.web.servlet;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ye.pojo.Brand;
import com.ye.pojo.PageBean;
import com.ye.pojo.User;
import com.ye.service.BrandService;
import com.ye.service.UserService;
import com.ye.service.impl.BrandServiceImpl;
import com.ye.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet {

    private final BrandService brandService = new BrandServiceImpl();
    private final UserService userService =new UserServiceImpl();

    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取session对象
        HttpSession session = request.getSession();
        //获取当前用户
        User user = (User) session.getAttribute("user");
        //按当前用户查询brands对象
        List<Brand> brands = brandService.selectAll(user.getId());
        //将brands对象序列化为JSON对象
        String brandString = JSON.toJSONString(brands);
        //设置响应消息头编码格式utf-8
        response.setContentType("text/json;charset=utf-8");
        //返回响应数据JSON给前端
        response.getWriter().write(brandString);
    }

    public void addBrand(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
            brandService.addBrand(brand);
            response.getWriter().write("success");
        } catch (Exception e) {
            response.getWriter().write("fail");
        }
        System.out.println("成功按路径分发");
    }

    public void updateBrand(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //Axios提交的数据是JSON格式，要用getReader方法读取。
        BufferedReader br = request.getReader();
        //读取JSON格式的字符串
        String params = br.readLine();
        //JSON格式字符串转换为Brand对象
        Brand brand = JSON.parseObject(params, Brand.class);
   /*     HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        brand.setUser(user.getId());*/
        try {
            brandService.updateBrand(brand);
            response.getWriter().write("success");
        } catch (Exception e) {
            response.getWriter().write("fail");
        }
    }



    public void deleteBrand(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //Axios提交的数据是JSON格式，要用getReader方法读取。
        BufferedReader br = request.getReader();
        //读取JSON格式的字符串
        String params = br.readLine();
        //JSON格式字符串转换为Brand对象
        Brand brand = JSON.parseObject(params, Brand.class);
        try {
            brandService.deleteBrand(brand);
            response.getWriter().write("success");
        } catch (Exception e) {
            response.getWriter().write("fail");
        }
    }




    public void deleteSelectBrand(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //Axios提交的数据是JSON格式，要用getReader方法读取。
        BufferedReader br = request.getReader();
        //读取JSON格式的字符串
        String params = br.readLine();
        //JSON格式字符串转换为Brand对象
        List<Brand> brands = JSON.parseArray(params, Brand.class);

        try {
            brandService.deleteSelectBrand(brands);
            response.getWriter().write("success");
        } catch (Exception e) {
            response.getWriter().write("fail");
        }
    }

    public void selectPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        //获取当前用户
        User user = (User) session.getAttribute("user");
        //Axios提交的数据是JSON格式，要用getReader方法读取。
        BufferedReader br = request.getReader();
        //读取JSON格式的字符串
        String params = br.readLine();
        JSONObject jsonObject = JSON.parseObject(params);
        int pageSize = (int)jsonObject.get("pageSize");
        int currentPage = ((int)jsonObject.get("currentPage")-1)*pageSize;
        List<Brand> brands = brandService.selectPage(user.getId(), currentPage, pageSize);
        //将brands对象序列化为JSON对象
        String brandString = JSON.toJSONString(brands);
        //设置响应消息头编码格式utf-8
        response.setContentType("text/json;charset=utf-8");
        //返回响应数据JSON给前端
        response.getWriter().write(brandString);
    }

    public void allBands(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        //获取当前用户
        User user = (User) session.getAttribute("user");

        int brands = brandService.allBrands(user.getId());
        //将brands对象序列化为JSON对象
        System.out.println(brands);
        String brandString = JSON.toJSONString(brands);
        //设置响应消息头编码格式utf-8
        response.setContentType("text/json;charset=utf-8");
        //返回响应数据JSON给前端
        response.getWriter().write(brandString);
    }


    public void selectByCondition(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        //获取当前用户
        User user = (User) session.getAttribute("user");

        BufferedReader br = request.getReader();
        //读取JSON格式的字符串
        String params = br.readLine();
        //JSON格式字符串转换为Brand对象
        JSONObject jsonObject = JSON.parseObject(params);
        int pageSize = (int)jsonObject.get("pageSize");
        int currentPage = ((int)jsonObject.get("currentPage")-1)*pageSize;
        Brand brand = jsonObject.getObject("brand",Brand.class);

        brand.setUser(user.getId());

        int count = brandService.selectByConditionCount(brand);
        if(count>0){
            List<Brand> brands = brandService.selectByCondition(brand,currentPage,pageSize);
            PageBean<Brand> pageBean = new PageBean<>();
            pageBean.setRows(brands);
            pageBean.setTotalCount(count);
            //将brands对象序列化为JSON对象
            String pageBeanString = JSON.toJSONString(pageBean);
            //设置响应消息头编码格式utf-8
            response.setContentType("text/json;charset=utf-8");

            //返回响应数据JSON给前端
            response.getWriter().write(pageBeanString);
        }else{
            response.getWriter().write("fail");
        }

    }

}
