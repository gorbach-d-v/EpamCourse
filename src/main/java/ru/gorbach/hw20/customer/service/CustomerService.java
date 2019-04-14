package ru.gorbach.hw20.customer.service;

import ru.gorbach.hw20.common.solutions.service.BaseService;
import ru.gorbach.hw20.customer.domain.Customer;
import ru.gorbach.hw20.customer.search.CustomerSearchCondition;

import java.util.List;

public interface CustomerService extends BaseService<Customer, Long> {

    List<Customer> search(CustomerSearchCondition searchCondition);

}
