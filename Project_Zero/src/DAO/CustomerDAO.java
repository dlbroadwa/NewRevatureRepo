package DAO;

import Application.Customer;

import java.util.ArrayList;

public interface CustomerDAO {
    public ArrayList<Customer> retrieveCustomers();
    public Customer retrieveCustomere(String username);
}
