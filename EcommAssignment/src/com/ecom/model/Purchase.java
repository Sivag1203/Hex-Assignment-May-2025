package com.ecom.model;

public class Purchase {
    private int id;
    private String date_of_purchase;
    private int customer_id;
    private int product_id;

    public Purchase(){};

    public Purchase(int id, String date_of_purchase, int product_id, int customer_id) {
        this.id = id;
        this.date_of_purchase = date_of_purchase;
        this.product_id = product_id;
        this.customer_id = customer_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate_of_purchase() {
        return date_of_purchase;
    }

    public void setDate_of_purchase(String date_of_purchase) {
        this.date_of_purchase = date_of_purchase;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", date_of_purchase='" + date_of_purchase + '\'' +
                ", customer_id=" + customer_id +
                ", product_id=" + product_id +
                '}';
    }
}
