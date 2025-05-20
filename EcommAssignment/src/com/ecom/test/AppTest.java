package com.ecom.test;

import com.ecom.model.Category;
import com.ecom.model.Customer;
import com.ecom.model.Product;
import com.ecom.model.Purchase;
import com.ecom.service.CategoryService;
import com.ecom.service.CustomerService;
import com.ecom.service.ProductService;
import com.ecom.service.PurchaseService;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    CustomerService customerService;
    CategoryService categoryService;
    ProductService productService;
    PurchaseService purchaseService;

    @BeforeEach
    public void init() {
        customerService = new CustomerService();
        categoryService = new CategoryService();
        productService = new ProductService();
        purchaseService = new PurchaseService();
    }

    @Test
    public void testCustomerInsert() {
        Customer c = new Customer();
        c.setName("John Doe");
        c.setAddress("123 Main St");
        assertDoesNotThrow(() -> customerService.insert(c));
    }

    @Test
    public void testCategoryInsert() {
        Category cat = new Category();
        cat.setName("Electronics");
        assertDoesNotThrow(() -> categoryService.insert(cat));
    }

    @Test
    public void testProductInsertAndFetchByCategory() {

        Category cat = new Category();
        cat.setName("Books");
        categoryService.insert(cat);

        Product p = new Product();
        p.setTitle("Java Programming");
        p.setPrice(499.99);
        p.setDescription("Comprehensive Java Guide");
        p.setCategory_id(1);

        assertDoesNotThrow(() -> productService.insert(p));

        List<Product> products = productService.getProductsByCategoryId(1);
        assertNotNull(products);
        assertFalse(products.isEmpty());
    }

    @Test
    public void testMakePurchase() {
        Purchase purchase = new Purchase();
        purchase.setCustomer_id(1);
        purchase.setProduct_id(1);
        purchase.setDate_of_purchase("2025-05-17");

        assertDoesNotThrow(() -> purchaseService.insert(purchase));
    }
}
