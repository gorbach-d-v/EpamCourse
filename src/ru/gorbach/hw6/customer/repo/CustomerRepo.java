package ru.gorbach.hw6.customer.repo;

import ru.gorbach.hw6.common.business.repo.BaseRepo;
import ru.gorbach.hw6.customer.domain.Customer;
import ru.gorbach.hw6.customer.search.CustomerSearchCondition;

import java.util.List;

public interface CustomerRepo extends BaseRepo {

    void add(Customer customer);

    Customer findById(long id);

    List<Customer> search(CustomerSearchCondition searchCondition);
}
