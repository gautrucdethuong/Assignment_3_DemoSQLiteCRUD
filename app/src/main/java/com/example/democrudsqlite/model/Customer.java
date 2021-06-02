package com.example.democrudsqlite.model;

/**
 * Author by HUYNH NHAT MINH (ミン).
 * Email: minhhnce140197@fpt.edu.vn.
 * Date on 6/1/2021.
 * Company: FPT大学.
 */

public class Customer {
    //Declare variable
    private int customerId;
    private String customerName;
    private String customerCode;
    private String customerUrl;

    // Declare constructor
    public Customer(int customerId, String customerName, String customerCode, String customerUrl) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerCode = customerCode;
        this.customerUrl = customerUrl;
    }

    // Declare constructor
    public Customer(String customerName, String customerCode, String customerUrl) {
        this.customerName = customerName;
        this.customerCode = customerCode;
        this.customerUrl = customerUrl;
    }

    // generation getter and setter
    public String getCustomerCode() {
        return customerCode;
    }

    public String getCustomerUrl() {
        return customerUrl;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getCustomerId() {
        return customerId;
    }

}
