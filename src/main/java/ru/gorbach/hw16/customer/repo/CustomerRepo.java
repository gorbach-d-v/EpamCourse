package ru.gorbach.hw16.customer.repo;

import ru.gorbach.hw16.customer.domain.Customer;
import ru.gorbach.hw16.common.solutions.repo.BaseRepo;
import ru.gorbach.hw16.customer.search.CustomerSearchCondition;

import java.util.List;

public interface CustomerRepo extends BaseRepo<Customer, Long> {
    List<Customer> search(CustomerSearchCondition searchCondition);
}
