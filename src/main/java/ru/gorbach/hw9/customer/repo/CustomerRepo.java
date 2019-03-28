package ru.gorbach.hw9.customer.repo;

import ru.gorbach.hw9.customer.domain.Customer;
import ru.gorbach.hw9.customer.search.CustomerSearchCondition;
import ru.gorbach.hw9.common.solutions.repo.BaseRepo;

import java.util.List;

public interface CustomerRepo extends BaseRepo<Customer, Long> {
    List<Customer> search(CustomerSearchCondition searchCondition);
}
