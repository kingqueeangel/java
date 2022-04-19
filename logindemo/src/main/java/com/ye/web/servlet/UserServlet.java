package com.ye.web.servlet;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ye.pojo.User;
import com.ye.service.UserService;
import com.ye.service.impl.UserServiceImpl;
import com.ye.util.CheckCodeUtil;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {

    private UserService service = new UserServiceImpl();

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //创建读取对象
        BufferedReader br = request.getReader();
        //读取浏览器发送的请求
        String params = br.readLine();
        //将请求数据转换为JSON字符串
        JSONObject jsonObject = JSON.parseObject(params);
        //从JSON字符串中提取用户名和密码和复选框勾选状态
        String username = (String) jsonObject.get("username");
        String password = (String) jsonObject.get("password");
        Object remember = jsonObject.get("remember");
        //根据用户名密码查询是否存在
        User user = service.select(username, password);
        //判断查询结果是否为空
        if (user != null) {
            //进入登陆界面
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            Cookie nameCookie;
            Cookie passwordCookie;
            //判断用户是否勾上记住用户名密码复选框
            if (remember.equals(true)) {
                //勾选了就设置cookie保存保存用户名和密码7天
                nameCookie = new Cookie("username", user.getUserName());
                passwordCookie = new Cookie("password", user.getPassword());
                nameCookie.setMaxAge(60 * 60 * 24 * 7);
                passwordCookie.setMaxAge(60 * 60 * 24 * 7);
            } else {
                //没有勾选就把浏览器cookie设置为空
                nameCookie = new Cookie("username", null);
                passwordCookie = new Cookie("password", null);
                nameCookie.setMaxAge(0);
                passwordCookie.setMaxAge(0);
            }
            //将Cookie数据写入浏览器
            response.addCookie(nameCookie);
            response.addCookie(passwordCookie);
            //登陆成功返回true
            response.getWriter().write("true");
            //查询的用户返回为空
        } else {
            //登录失败返回false
            response.getWriter().write("false");

        }
    }


    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //创建读取对象
        BufferedReader br = request.getReader();
        //读取浏览器发送的请求
        String params = br.readLine();
        //将请求数据转换为JSON字符串
        JSONObject jsonObject = JSON.parseObject(params);
        //从JSON字符串中提取用户名
        String username = (String) jsonObject.get("username");
        //查询该用户名是否存在
        User user = service.selectByName(username);
        //判断查询结果
        if (user == null) {
            //结果为空，允许注册
            user = JSON.parseObject(params, User.class);
            service.add(user);
            response.getWriter().write("success");
        } else {
            //用户存在
            response.getWriter().write("fail");
        }


    }

    public void userNameIsExits(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String userName = request.getParameter("username");
        User user = service.selectByName(userName);
        if (user != null) {
            response.getWriter().write("true");
        } else {
            response.getWriter().write("false");
        }

    }


    public void checkCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取用户填写的验证码
        String checkCode = request.getParameter("checkCode");
        //获取服务器端生成的验证码
        String checkCodeGen = (String) request.getSession().getAttribute("checkCodeGen");
        //检查用户填写的验证码与服务端生成的验证码是否匹配
        if (checkCodeGen.equalsIgnoreCase(checkCode)) {
            response.getWriter().write("true");
        } else {
            response.getWriter().write("false");
        }
    }

    public void createCheckCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletOutputStream os = response.getOutputStream();
        String checkCode = CheckCodeUtil.outputVerifyImage(100, 50, os, 4);
        HttpSession session = request.getSession();
        session.setAttribute("checkCodeGen", checkCode);
    }

    public void exitUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("/login/login.html");
    }

    public void user(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String userString = JSON.toJSONString(user);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(userString);


    }

}