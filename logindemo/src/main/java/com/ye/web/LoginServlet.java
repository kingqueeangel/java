package com.ye.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ye.pojo.User;
import com.ye.service.UserService;
import com.ye.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/loginServlet")
public class LoginServlet extends HttpServlet {
    private UserService service = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BufferedReader br = request.getReader();
        String params = br.readLine();
        JSONObject jsonObject = JSON.parseObject(params);
        String username = (String)jsonObject.get("username");
        String password = (String)jsonObject.get("password");
        Object remember = jsonObject.get("remember");
        User user = service.select(username,password);
        if (user != null) {
            //进入登陆界面
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            Cookie nameCookie;
            Cookie passwordCookie;

            if (remember.equals(true)) {
                nameCookie = new Cookie("username", user.getUserName());
                passwordCookie = new Cookie("password", user.getPassword());
                nameCookie.setMaxAge(60 * 60 * 24 * 7);
                passwordCookie.setMaxAge(60 * 60 * 24 * 7);
            } else {
                nameCookie = new Cookie("username", null);
                passwordCookie = new Cookie("password", null);
                nameCookie.setMaxAge(0);
                passwordCookie.setMaxAge(0);
            }
            response.addCookie(nameCookie);
            response.addCookie(passwordCookie);
            response.getWriter().write("true");
           /* String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/selectAllServlet");*/
        } else {
            //进入登陆失败界面
            response.getWriter().write("false");
            /*request.setAttribute("login_msg", "用户名或密码错误");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
       */
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
