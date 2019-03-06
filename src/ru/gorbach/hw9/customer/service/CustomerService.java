package ru.gorbach.hw9.customer.service;

import ru.gorbach.hw9.common.business.service.BaseService;
import ru.gorbach.hw9.customer.domain.Customer;
import ru.gorbach.hw9.customer.search.CustomerSearchCondition;

import java.util.List;

public interface CustomerService extends BaseService {

    void add(Customer customer);

    void update(Customer customer);

    Customer findById(Long id);

    void delete(Customer customer);

    List<Customer> search(CustomerSearchCondition searchCondition);
}
