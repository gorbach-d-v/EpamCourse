package ru.gorbach.hw14.customer.service;

import ru.gorbach.hw14.common.solutions.paginationutils.Pagination;
import ru.gorbach.hw14.common.solutions.service.BaseService;
import ru.gorbach.hw14.customer.domain.Customer;
import ru.gorbach.hw14.customer.search.CustomerSearchCondition;

import java.util.List;

public interface CustomerService extends BaseService<Customer, Long> {

    List<Customer> search(CustomerSearchCondition searchCondition, Pagination pagination);

}
