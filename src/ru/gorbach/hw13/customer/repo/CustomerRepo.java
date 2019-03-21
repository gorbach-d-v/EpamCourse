package ru.gorbach.hw13.customer.repo;

import ru.gorbach.hw13.common.solutions.repo.BaseRepo;
import ru.gorbach.hw13.customer.domain.Customer;
import ru.gorbach.hw13.customer.search.CustomerSearchCondition;

import java.util.List;

public interface CustomerRepo extends BaseRepo<Customer, Long> {
    List<Customer> search(CustomerSearchCondition searchCondition);
}
