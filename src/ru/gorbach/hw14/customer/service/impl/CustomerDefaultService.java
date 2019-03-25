package ru.gorbach.hw14.customer.service.impl;

import ru.gorbach.hw14.customer.domain.Customer;
import ru.gorbach.hw14.customer.repo.CustomerRepo;
import ru.gorbach.hw14.customer.search.CustomerSearchCondition;
import ru.gorbach.hw14.customer.service.CustomerService;
import ru.gorbach.hw14.order.domain.Order;
import ru.gorbach.hw14.order.repo.OrderRepo;
import ru.gorbach.hw14.order.service.OrderService;

import java.util.Collections;
import java.util.List;

public class CustomerDefaultService implements CustomerService {
    private CustomerRepo customerRepo;
    private OrderRepo orderRepo;
    private OrderService orderService;

    public CustomerDefaultService(CustomerRepo customerRepo, OrderRepo orderRepo) {
        this.customerRepo = customerRepo;
        this.orderRepo = orderRepo;
    }

    @Override
    public void add(Customer customer) {
        customerRepo.add(customer);

        if (customer.getOrders() != null) {
            for (Order order : customer.getOrders()) {
                orderRepo.add(order);
            }
        }
    }

    @Override
    public void update(Customer customer) {
        if (customer.getId() != null) {
            customerRepo.update(customer);
        }
    }

    @Override
    public Customer findById(Long id) {
        if (id != null) {
            return customerRepo.findById(id);
        } else {
            return null;
        }
    }

    @Override
    public List<Customer> search(CustomerSearchCondition searchCondition) {
        return customerRepo.search(searchCondition);
    }

    @Override
    public void deleteById(Long id) {
        if (id != null) {
            removeAllOrdersFromCustomer(id);
            customerRepo.deleteById(id);
        }
    }

    @Override
    public void delete(Customer customer) {
        if (customer.getId() != null) {
            customerRepo.deleteById(customer.getId());
        }
    }

    @Override
    public void printAll() {
        customerRepo.printAll();
    }

    private void removeAllOrdersFromCustomer(Long customerId) {
        Customer customer = findById(customerId);
        if (customer != null) {
            List<Order> orders = customer.getOrders() == null ? Collections.emptyList() : customer.getOrders();
            for (Order order : orders) {
                orderService.deleteById(order.getId());
            }
        }
    }
}
