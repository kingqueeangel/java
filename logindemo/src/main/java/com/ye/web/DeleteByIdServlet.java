package com.ye.web;



import com.ye.service.BrandService;
import com.ye.service.UserService;
import com.ye.service.impl.BrandServiceImpl;
import com.ye.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteByIdServlet", value = "/deleteByIdServlet")
public class DeleteByIdServlet extends HttpServlet {
    BrandService brandService = new BrandServiceImpl();
    UserService userService =new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        brandService.deleteBrandById(Integer.parseInt(id));
        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + "/selectAllServlet");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
