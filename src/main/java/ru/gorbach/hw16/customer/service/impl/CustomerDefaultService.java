package ru.gorbach.hw16.customer.service.impl;

import ru.gorbach.hw16.customer.domain.Customer;
import ru.gorbach.hw16.customer.repo.CustomerRepo;
import ru.gorbach.hw16.customer.service.CustomerService;
import ru.gorbach.hw16.customer.search.CustomerSearchCondition;
import ru.gorbach.hw16.order.domain.Order;
import ru.gorbach.hw16.order.repo.OrderRepo;
import ru.gorbach.hw16.order.service.OrderService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CustomerDefaultService implements CustomerService {
    private CustomerRepo customerRepo;
    private OrderService orderService;

    public CustomerDefaultService(CustomerRepo customerRepo, OrderService orderService) {
        this.customerRepo = customerRepo;
        this.orderService = orderService;
    }

    @Override
    public void add(Customer customer) {
        if (customer != null) {
            customerRepo.add(customer);
            orderService.add(customer.getOrders());
        }
    }

    @Override
    public void add(Collection<Customer> customersList) {
        if (customersList!=null){
            for (Customer customer : customersList) {
                add(customer);
            }
        }
    }

    @Override
    public void update(Customer customer) {
        if (customer != null) {
            if (customer.getId() != null) {
                customerRepo.update(customer);
            }
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
        if (customer != null) {
            if (customer.getId() != null) {
                customerRepo.deleteById(customer.getId());
            }
        }
    }

    @Override
    public void delete(Collection<Customer> customersList) {
        if (customersList != null){
            for (Customer customer : customersList){
                add(customer);
            }
        }
    }

    @Override
    public void printAll() {
        customerRepo.printAll();
    }

    @Override
    public List<Customer> findAll() {
        return customerRepo.findAll();
    }

    @Override
    public int countAll() {
        return customerRepo.countAll();
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
