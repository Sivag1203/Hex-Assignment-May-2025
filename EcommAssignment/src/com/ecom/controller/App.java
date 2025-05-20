package com.ecom.controller;

import com.ecom.model.Category;
import com.ecom.model.Customer;
import com.ecom.model.Product;
import com.ecom.model.Purchase;
import com.ecom.service.CategoryService;
import com.ecom.service.CustomerService;
import com.ecom.service.ProductService;
import com.ecom.service.PurchaseService;

import java.util.Scanner;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("Choose the option:\n0.Exit\n1.Add Customers\n2.Add Products\n3.Add Category\n4.Make a purchase\n5.Fetch All Products in a Category\n");
            int choice = sc.nextInt();
            sc.nextLine();
            if (choice ==0) break;
            switch (choice){
                case 1:
                    Customer c = new Customer();
                    System.out.println("Enter Customer name: ");
                    c.setName(sc.nextLine());
                    System.out.println("Enter Address: ");
                    c.setAddress(sc.nextLine());
                    CustomerService Cser = new CustomerService();
                    Cser.insert(c);
                    System.out.println("Successfully Inserted Customer");
                    break;
                case 2:
                    Product p = new Product();
                    ProductService Pser = new ProductService();
                    System.out.println("Enter product name: ");
                    p.setTitle(sc.nextLine());
                    System.out.println("Enter price of thr product: ");
                    p.setPrice(sc.nextDouble());
                    sc.nextLine();
                    System.out.println("Enter product description: ");
                    p.setDescription(sc.nextLine());
                    System.out.println("Enter Category ID:");
                    p.setCategory_id(sc.nextInt());
                    Pser.insert(p);
                    break;
                case 3:
                    Category cat = new Category();
                    CategoryService catService = new CategoryService();
                    System.out.println("Enter Category name: ");
                    cat.setName(sc.nextLine());
                    catService.insert(cat);
                    System.out.println("Successfully Inserted Category");
                    break;
                case 4:
                    Purchase purchase = new Purchase();
                    PurchaseService purchaseService = new PurchaseService();
                    System.out.println("Enter Customer ID: ");
                    purchase.setCustomer_id(sc.nextInt());
                    System.out.println("Enter Product ID: ");
                    purchase.setProduct_id(sc.nextInt());
                    // Optional: Set current date as purchase date
                    purchase.setDate_of_purchase(java.time.LocalDate.now().toString());
                    purchaseService.insert(purchase);
                    System.out.println("Purchase Successful");
                    break;
                case 5:
                    ProductService productService = new ProductService();
                    System.out.println("Enter Category ID to fetch products: ");
                    int catId = sc.nextInt();
                    List<Product> products = productService.getProductsByCategoryId(catId);

                    if (products == null || products.isEmpty()) {
                        System.out.println("No products found in this category.");
                    } else {
                        for (Product prod : products) {
                            System.out.println(prod.toString());
                        }
                    }
                    break;
                default:
                    System.out.println("Invalid option. Please choose a valid one.");
                    break;
            }
        }
    }
}
