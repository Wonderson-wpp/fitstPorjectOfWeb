package com.peng.bookstore.cart.web.servlet;

import com.peng.bookstore.book.dao.BookDao;
import com.peng.bookstore.book.domain.Book;
import com.peng.bookstore.book.service.BookService;
import com.peng.bookstore.cart.domain.Cart;
import com.peng.bookstore.cart.domain.CartItem;
import com.peng.bookstore.utils.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "CartServlet", urlPatterns = "/CartServlet")
public class CartServlet extends BaseServlet {
    private BookService bookService = new BookService();

    public String addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int count = Integer.parseInt(request.getParameter("count"));
        String bid = request.getParameter("bid");
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        CartItem cartItem = new CartItem();
        cartItem.setCount(count);
        cartItem.setBook(bookService.load(bid));

        cart.add(cartItem);
        Collection<CartItem> items = cart.getCartItems();
        request.setAttribute("items", items);
        return "/jsps/cart/list.jsp";
    }

    public String clear(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        cart.clear();
        session.setAttribute("cart", cart);
        request.setAttribute("items", cart.getCartItems());
        return "/jsps/cart/list.jsp";
    }

    public String delete(HttpServletRequest request, HttpServletResponse response) {
        String bid = request.getParameter("bid");
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        cart.delete(bid);
        session.setAttribute("cart", cart);
        request.setAttribute("items", cart.getCartItems());
        return "/jsps/cart/list.jsp";
    }
}
