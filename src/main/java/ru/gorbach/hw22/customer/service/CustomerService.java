package ru.gorbach.hw22.customer.service;

import ru.gorbach.hw22.common.solutions.service.BaseService;
import ru.gorbach.hw22.customer.domain.Customer;
import ru.gorbach.hw22.customer.search.CustomerSearchCondition;

import java.util.List;

public interface CustomerService extends BaseService<Customer, Long> {

    List<Customer> search(CustomerSearchCondition searchCondition);

}
