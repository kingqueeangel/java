package com.ye.web;

import com.ye.pojo.User;
import com.ye.service.UserService;
import com.ye.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RegisterServlet", value = "/registerServlet")
public class RegisterServlet extends HttpServlet {
    private UserService service = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String checkCode = request.getParameter("checkCode");
        String checkCodeGen = (String)request.getSession().getAttribute("checkCodeGen");
        if(!checkCodeGen.equalsIgnoreCase(checkCode)){
            request.setAttribute("checkCode_msg", "验证码错误");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }
        String userName = request.getParameter("userName");
        User user = service.selectByName(userName);
        String msg = null;
        String path = null;
        if (user == null) {
            String password = request.getParameter("password");
            User newUser = new User();
            newUser.setUserName(userName);
            newUser.setPassword(password);
            service.add(newUser);
            msg = "注册成功，请登录";
            path = "/login.jsp";
        } else {
            //用户存在
            msg = "用户名已存在";
            path = "/register.jsp";
        }
        request.setAttribute("register_msg", msg);
        request.getRequestDispatcher(path).forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
