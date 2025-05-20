package com.ecom.service;

import com.ecom.dao.PurchaseDao;
import com.ecom.daoImpl.PurchaseDaoImpl;
import com.ecom.model.Purchase;

public class PurchaseService {

    private PurchaseDao purchaseDao;

    public PurchaseService() {
        this.purchaseDao = new PurchaseDaoImpl();
    }

    public void insert(Purchase p) {
        purchaseDao.makePurchase(p);
    }
}
