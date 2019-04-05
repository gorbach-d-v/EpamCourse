package ru.gorbach.hw17.customer.service;

import ru.gorbach.hw17.customer.search.CustomerSearchCondition;
import ru.gorbach.hw17.common.solutions.service.BaseService;
import ru.gorbach.hw17.customer.domain.Customer;

import java.util.List;

public interface CustomerService extends BaseService<Customer, Long> {

    List<Customer> search(CustomerSearchCondition searchCondition);

}
