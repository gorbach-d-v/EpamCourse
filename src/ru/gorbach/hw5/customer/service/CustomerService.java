package ru.gorbach.hw5.customer.service;

import ru.gorbach.hw5.common.business.service.BaseService;
import ru.gorbach.hw5.customer.domain.BaseCustomer;
import ru.gorbach.hw5.customer.search.CustomerSearchCondition;

public interface CustomerService extends BaseService {

    void add(BaseCustomer customer);

    BaseCustomer findById(long id);

    BaseCustomer[] search(CustomerSearchCondition searchCondition);
}
