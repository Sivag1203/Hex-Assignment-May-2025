package com.ecom.service;

import com.ecom.daoImpl.CustomerDaoImpl;
import com.ecom.model.Customer;

public class CustomerService {
    CustomerDaoImpl dao = new CustomerDaoImpl();
    public void insert(Customer c){
        dao.insert(c);
    }
}
