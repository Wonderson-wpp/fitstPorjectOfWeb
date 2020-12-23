package com.peng.bookstore.book.dao;

import com.peng.bookstore.book.domain.Book;
import com.peng.bookstore.utils.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class BookDao {

    private QueryRunner queryRunner = new TxQueryRunner();

    public List<Book> findAll() {
        try {
            return queryRunner.query("select * from book", new BeanListHandler<Book>(Book.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Book> findByCid(String cid) {
        try {
            return queryRunner.query("select * from book where cid=?", new BeanListHandler<Book>(Book.class)
            , cid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Book findByBid(String bid) {
        try {
            return queryRunner.query("select * from book where bid = ?", new BeanHandler<>(Book.class), bid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
