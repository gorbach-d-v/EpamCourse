package ru.gorbach.hw5.customer.repo;

import ru.gorbach.hw5.common.business.repo.BaseRepo;
import ru.gorbach.hw5.customer.domain.BaseCustomer;
import ru.gorbach.hw5.customer.search.CustomerSearchCondition;

public interface CustomerRepo extends BaseRepo {

    void add(BaseCustomer customer);

    BaseCustomer findById(long id);

    BaseCustomer[] search(CustomerSearchCondition searchCondition);
}
