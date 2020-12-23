package com.peng.bookstore.category.dao;

import com.peng.bookstore.category.domain.Category;
import com.peng.bookstore.utils.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class CategoryDao {
    private QueryRunner queryRunner = new TxQueryRunner();

    public List<Category> findAll() {
        try {
            return queryRunner.query("select * from category ", new BeanListHandler<Category>(Category.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
