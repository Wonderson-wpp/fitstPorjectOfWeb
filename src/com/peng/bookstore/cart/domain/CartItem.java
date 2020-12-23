package com.peng.bookstore.cart.domain;

import com.peng.bookstore.book.domain.Book;
import com.peng.bookstore.book.service.BookService;

import java.math.BigDecimal;

public class CartItem {

    private Book book;
    private int count;


    public double getSubtotal() {

        BigDecimal c = new BigDecimal(count);
        BigDecimal p = new BigDecimal(book.getPrice());

        return c.multiply(p).doubleValue();
    }
    @Override
    public String toString() {
        return "CartItem{" +
                "book=" + book +
                ", count=" + count +
                '}';
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
