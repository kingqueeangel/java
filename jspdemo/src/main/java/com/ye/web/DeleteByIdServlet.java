package com.ye.web;

import com.ye.service.BrandService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteByIdServletServlet", value = "/deleteByIdServlet")
public class DeleteByIdServlet extends HttpServlet {
    BrandService service = new BrandService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        service.deleteById(Integer.parseInt(id));
        request.getRequestDispatcher("/selectAllServlet").forward(request,response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
