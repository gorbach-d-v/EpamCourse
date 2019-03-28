package ru.gorbach.hw9.customer.service;

import ru.gorbach.hw9.customer.domain.Customer;
import ru.gorbach.hw9.customer.search.CustomerSearchCondition;
import ru.gorbach.hw9.common.solutions.service.BaseService;

import java.util.List;

public interface CustomerService extends BaseService <Customer,Long>{

    List<Customer> search(CustomerSearchCondition searchCondition);

}
