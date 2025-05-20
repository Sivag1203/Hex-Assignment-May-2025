package com.ecom.service;

import com.ecom.dao.CategoryDao;
import com.ecom.daoImpl.CategoryDaoImpl;
import com.ecom.model.Category;

public class CategoryService {
CategoryDao dao = new CategoryDaoImpl();

    public void insert(Category c) {
        dao.insert(c);
    }
}