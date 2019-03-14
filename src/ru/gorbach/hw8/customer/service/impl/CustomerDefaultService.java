package ru.gorbach.hw8.customer.service.impl;

import ru.gorbach.hw8.customer.domain.Customer;
import ru.gorbach.hw8.customer.repo.CustomerRepo;
import ru.gorbach.hw8.customer.search.CustomerSearchCondition;
import ru.gorbach.hw8.customer.service.CustomerService;
import ru.gorbach.hw8.order.domain.Order;
import ru.gorbach.hw8.order.repo.OrderRepo;

import java.util.List;

public class CustomerDefaultService implements CustomerService {
    private CustomerRepo customerRepo;
    private OrderRepo orderRepo;

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
}
