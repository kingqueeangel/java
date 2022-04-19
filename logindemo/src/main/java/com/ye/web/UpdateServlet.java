package com.ye.web;

import com.ye.pojo.Brand;
import com.ye.pojo.User;
import com.ye.service.BrandService;
import com.ye.service.UserService;
import com.ye.service.impl.BrandServiceImpl;
import com.ye.service.impl.UserServiceImpl;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UpdateServlet", value = "/updateServlet")
public class UpdateServlet extends HttpServlet {

    private final UserService userService=new UserServiceImpl();
    private final BrandService brandService = new BrandServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        request.setCharacterEncoding("UTF-8");
        Brand brand = new Brand();
        String id = request.getParameter("id");
        String brandName = request.getParameter("brandName");
        String companyName = request.getParameter("companyName");
        String ordered = request.getParameter("ordered");
        String description = request.getParameter("description");
        String status = request.getParameter("status");
        brand.setUser(user.getId());
        brand.setBrandName(brandName);
        brand.setCompanyName(companyName);
        brand.setOrdered(Integer.parseInt(ordered));
        brand.setDescription(description);
        brand.setStatus(Integer.parseInt(status));
        brand.setId(Integer.parseInt(id));
        brandService.updateBrand(brand);
        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + "/selectAllServlet");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
