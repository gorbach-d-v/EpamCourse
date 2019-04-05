package ru.gorbach.hw17.customer.repo;

import ru.gorbach.hw17.common.solutions.repo.BaseRepo;
import ru.gorbach.hw17.customer.domain.Customer;
import ru.gorbach.hw17.customer.search.CustomerSearchCondition;

import java.util.List;

public interface CustomerRepo extends BaseRepo<Customer, Long> {
    List<Customer> search(CustomerSearchCondition searchCondition);
}
