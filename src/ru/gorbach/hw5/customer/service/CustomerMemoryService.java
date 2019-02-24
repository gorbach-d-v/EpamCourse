package ru.gorbach.hw5.customer.service;

import ru.gorbach.hw5.customer.Customer;
import ru.gorbach.hw5.customer.repo.CustomerMemoryRepo;
import ru.gorbach.hw5.order.Order;
import ru.gorbach.hw5.order.repo.OrderMemoryRepo;

public class CustomerMemoryService implements CustomerServiceI{
    private CustomerMemoryRepo customerRepo = new CustomerMemoryRepo();
    private OrderMemoryRepo orderRepo = new OrderMemoryRepo();

    public void addCustomer(Customer customer) {
        customerRepo.addCustomer(customer);

        if (customer.getOrders() != null) {
            for (Order order : customer.getOrders()) {
                orderRepo.addOrder(order);
            }
        }
    }

    public Customer findCustomerById(long id) {
        return customerRepo.findCustomerById(id);
    }

    public void deleteCustomer(Customer customer) {
        customerRepo.deleteCustomer(customer);
    }

    public void deleteCustomer(Long id) {
        customerRepo.deleteCustomer(id);
    }

    public void printCustomers() {
        customerRepo.printCustomers();
    }
}
