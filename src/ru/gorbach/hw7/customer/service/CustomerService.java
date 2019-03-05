package ru.gorbach.hw7.customer.service;

import ru.gorbach.hw7.customer.domain.Customer;
import ru.gorbach.hw7.common.business.service.BaseService;
import ru.gorbach.hw7.customer.search.CustomerSearchCondition;

import java.util.List;

public interface CustomerService extends BaseService {

    void add(Customer customer);

    void update(Customer customer);

    Customer findById(Long id);

    void delete(Customer customer);

    List<Customer> search(CustomerSearchCondition searchCondition);
}
