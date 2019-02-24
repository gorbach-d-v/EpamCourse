package ru.gorbach.hw5.customer.service;

import ru.gorbach.hw5.customer.Customer;

public interface CustomerServiceI {
    void addCustomer(Customer customer);

    Customer findCustomerById(long id);

    void deleteCustomer(Customer customer);

    void deleteCustomer(Long id);

    void printCustomers();
}
