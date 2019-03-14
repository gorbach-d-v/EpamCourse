package ru.gorbach.hw8.customer.repo;

import ru.gorbach.hw8.common.business.repo.BaseRepo;
import ru.gorbach.hw8.customer.domain.Customer;
import ru.gorbach.hw8.customer.search.CustomerSearchCondition;

import java.util.List;

public interface CustomerRepo extends BaseRepo {

    void add(Customer customer);

    void update(Customer customer);

    Customer findById(long id);

    List<Customer> search(CustomerSearchCondition searchCondition);
}
