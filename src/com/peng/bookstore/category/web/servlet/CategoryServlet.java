package com.peng.bookstore.category.web.servlet;

import com.peng.bookstore.category.domain.Category;
import com.peng.bookstore.category.service.CategoryService;
import com.peng.bookstore.utils.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CategoryServlet", urlPatterns = "/CategoryServlet")
public class CategoryServlet extends  BaseServlet {

    private CategoryService categoryService = new CategoryService();

    public String findAll(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException{

        request.setAttribute("cl", categoryService.findAll());
        return "/jsps/left.jsp";
    }
}
