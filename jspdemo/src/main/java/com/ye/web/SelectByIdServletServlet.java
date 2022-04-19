package com.ye.web;

import com.ye.pojo.Brand;
import com.ye.service.BrandService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "SelectByIdServletServlet", value = "/selectByIdServlet")
public class SelectByIdServletServlet extends HttpServlet {
    private BrandService service =new BrandService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        Brand brand = service.selectById(Integer.parseInt(id));
        request.setAttribute("brand",brand);

        request.getRequestDispatcher("/update.jsp").forward(request,response);



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
