package ru.gorbach.hw19.customer.repo;

import ru.gorbach.hw19.common.solutions.repo.BaseRepo;
import ru.gorbach.hw19.customer.domain.Customer;
import ru.gorbach.hw19.customer.search.CustomerSearchCondition;

import java.util.List;

public interface CustomerRepo extends BaseRepo<Customer, Long> {
    List<Customer> search(CustomerSearchCondition searchCondition);
}
