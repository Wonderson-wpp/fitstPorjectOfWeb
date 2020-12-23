package com.peng.bookstore.category.service;

import com.peng.bookstore.category.dao.CategoryDao;
import com.peng.bookstore.category.domain.Category;

import java.util.List;

public class CategoryService {
    private CategoryDao categoryDao = new CategoryDao();

    public List<Category> findAll() {
        return categoryDao.findAll();
    }
}
