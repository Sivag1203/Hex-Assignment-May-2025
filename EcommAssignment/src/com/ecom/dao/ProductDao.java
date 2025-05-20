package com.ecom.dao;

import com.ecom.model.Product;

import java.util.List;

public interface ProductDao {
    public void insert(Product p);
    public List<Product> getAllProdByCategory(int category_id);
}
