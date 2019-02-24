package ru.gorbach.hw5.customer.repo;

import ru.gorbach.hw5.customer.Customer;

public interface CustomerRepoI {
    void addCustomer(Customer customer);

    Customer findCustomerById(long id);

    void deleteCustomer(Customer customer);

    void deleteCustomer(Long id);

    void printCustomers();
}
