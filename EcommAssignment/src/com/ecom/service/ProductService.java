package com.ecom.service;

import com.ecom.daoImpl.ProductDoaImpl;
import com.ecom.model.Product;

import java.util.List;

public class ProductService {
    ProductDoaImpl dao = new ProductDoaImpl();

    public void insert(Product p) {
        dao.insert(p);
    }

    public List<Product> getProductsByCategoryId(int category_id) {
        return dao.getAllProdByCategory(category_id);
    }
}
