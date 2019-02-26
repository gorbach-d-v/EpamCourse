package ru.gorbach.hw5.customer.service.impl;

import ru.gorbach.hw5.customer.domain.BaseCustomer;
import ru.gorbach.hw5.customer.repo.CustomerRepo;
import ru.gorbach.hw5.customer.search.CustomerSearchCondition;
import ru.gorbach.hw5.customer.service.CustomerService;
import ru.gorbach.hw5.order.domain.Order;
import ru.gorbach.hw5.order.repo.OrderRepo;

public class CustomerDefaultService implements CustomerService {
    private CustomerRepo customerRepo;
    private OrderRepo orderRepo;

    @Override
    public void add(BaseCustomer customer) {
        customerRepo.add(customer);

        if (customer.getOrders() != null) {
            for (Order order : customer.getOrders()) {
                orderRepo.add(order);
            }
        }
    }

    @Override
    public BaseCustomer findById(long id) {
        return customerRepo.findById(id);
    }

    @Override
    public BaseCustomer[] search(CustomerSearchCondition searchCondition) { return customerRepo.search(searchCondition); }

    @Override
    public void deleteById(Long id) {
        customerRepo.deleteById(id);
    }

    @Override
    public void printAll() {
        customerRepo.printAll();
    }
}
