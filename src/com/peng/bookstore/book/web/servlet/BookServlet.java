package com.peng.bookstore.book.web.servlet;

import com.peng.bookstore.book.domain.Book;
import com.peng.bookstore.book.service.BookService;
import com.peng.bookstore.utils.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BookServlet", urlPatterns = "/BookServlet")
public class BookServlet extends BaseServlet {

    private BookService bookService = new BookService();

    public String findAll(HttpServletRequest request, HttpServletResponse response ) throws IOException, ServletException{

        List<Book> bookList = bookService.findAll();
        request.setAttribute("bl", bookList);
        return "/jsps/book/list.jsp";
    }

    public String findByCategory(HttpServletRequest request, HttpServletResponse response ) throws IOException, ServletException{

        String cid = request.getParameter("cid");

        if (cid != null)
        {request.setAttribute("bl", bookService.findByCategory(cid));}
        return "/jsps/book/list.jsp";
    }
    public String load(HttpServletRequest request, HttpServletResponse response ) throws IOException, ServletException{

        String bid = request.getParameter("bid");

        request.setAttribute("b", bookService.load(bid));
        return "/jsps/book/desc.jsp";
    }
    
    


}
