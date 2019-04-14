package ru.gorbach.hw20.customer.repo;

import ru.gorbach.hw20.common.solutions.repo.BaseRepo;
import ru.gorbach.hw20.customer.domain.Customer;
import ru.gorbach.hw20.customer.search.CustomerSearchCondition;

import java.util.List;

public interface CustomerRepo extends BaseRepo<Customer, Long> {
    List<Customer> search(CustomerSearchCondition searchCondition);
}
