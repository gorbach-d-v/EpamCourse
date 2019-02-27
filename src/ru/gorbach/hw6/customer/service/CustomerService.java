package ru.gorbach.hw6.customer.service;

import ru.gorbach.hw6.common.business.service.BaseService;
import ru.gorbach.hw6.customer.domain.Customer;
import ru.gorbach.hw6.customer.search.CustomerSearchCondition;

import java.util.List;

public interface CustomerService extends BaseService {

    void add(Customer customer);

    Customer findById(Long id);

    void delete(Customer customer);

    List<Customer> search(CustomerSearchCondition searchCondition);
}
