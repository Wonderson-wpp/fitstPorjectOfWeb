package com.peng.bookstore.book.service;

import com.peng.bookstore.book.dao.BookDao;
import com.peng.bookstore.book.domain.Book;


import java.util.List;

public class BookService {

    private BookDao bookDao = new BookDao();

    public List<Book> findAll() {
        return bookDao.findAll();
    }

    public List<Book> findByCategory(String cid) {
        return bookDao.findByCid(cid);
    }

    public Book load(String bid) {
        return bookDao.findByBid(bid);
    }
}
