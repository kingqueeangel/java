package com.ye.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/login/login/sdf")
public class LoginFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String[] urls = {"/login.jsp", "/imgs/", "/css/", "/loginServlet", "register.jsp", "/registerServlet", "/checkCodeServlet","/ajax-demo.html","/usernameServlet","/axios-demo.html","/ajaxServlet","/axiosServlet","/js/"};
        String index = "http://localhost/login/";
        String s = req.getRequestURL().toString();

        for (String url : urls) {
            if (s.contains(url)) {
                chain.doFilter(request, response);
                System.out.println("YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY");
                System.out.println(s);
                return;
            }
        }

        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        HttpSession session = req.getSession();
        Object user = session.getAttribute("user");
        if (user != null) {
            chain.doFilter(request, response);
            if (s.contains(".jsp")) {
                req.setAttribute("error_msg", "不能访问："+s);
                req.getRequestDispatcher("/error.html").forward(req, response);
            }
        } else {
            if(s.equals(index)){
                req.getRequestDispatcher("/login.jsp").forward(req, response);
                return;
            }
            req.setAttribute("login_msg", "您尚未登陆");
            req.getRequestDispatcher("/login.jsp").forward(req, response);
        }

    }

    @Override
    public void destroy() {

    }
}
