package ru.gorbach.hw22.customer.service.impl;

import ru.gorbach.hw22.customer.domain.Customer;
import ru.gorbach.hw22.customer.repo.CustomerRepo;
import ru.gorbach.hw22.customer.search.CustomerSearchCondition;
import ru.gorbach.hw22.customer.service.CustomerService;
import ru.gorbach.hw22.order.service.OrderService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class CustomerDefaultService implements CustomerService {
    private CustomerRepo customerRepo;
    private OrderService orderService;

    public CustomerDefaultService(CustomerRepo customerRepo, OrderService orderService) {
        this.customerRepo = customerRepo;
        this.orderService = orderService;
    }

    @Override
    public Customer add(Customer customer) {
        if (customer != null) {
            customerRepo.add(customer);
            orderService.add(customer.getOrders());
        }
        return customer;
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
    public Optional<Customer> findById(Long id) {
        if (id != null) {
            return customerRepo.findById(id);
        } else {
            return Optional.empty();
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
        findById(customerId).ifPresent(customer -> {
            if (customer.getOrders() != null) {
                customer.getOrders().forEach(order -> orderService.deleteById(order.getId()));
            }
        });
    }
}
