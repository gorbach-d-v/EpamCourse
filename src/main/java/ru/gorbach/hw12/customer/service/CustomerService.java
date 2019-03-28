package ru.gorbach.hw12.customer.service;

import ru.gorbach.hw12.common.solutions.service.BaseService;
import ru.gorbach.hw12.customer.search.CustomerSearchCondition;
import ru.gorbach.hw12.customer.domain.Customer;

import java.util.List;

public interface CustomerService extends BaseService<Customer, Long> {

    List<Customer> search(CustomerSearchCondition searchCondition);

}
