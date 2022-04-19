package com.ye.web;

import com.ye.pojo.Brand;
import com.ye.service.BrandService;
import com.ye.service.impl.BrandServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "SelectByIdServlet", value = "/selectByIdServlet")
public class SelectByIdServlet extends HttpServlet {
    BrandService brandService = new BrandServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        Brand brand = brandService.selectBrandById(Integer.parseInt(id));
        request.setAttribute("brand",brand);
        request.getRequestDispatcher("/update.html").forward(request,response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
