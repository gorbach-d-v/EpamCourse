package ru.gorbach.hw7.customer.repo;

import ru.gorbach.hw7.customer.domain.Customer;
import ru.gorbach.hw7.common.business.repo.BaseRepo;
import ru.gorbach.hw7.customer.search.CustomerSearchCondition;

import java.util.List;

public interface CustomerRepo extends BaseRepo {

    void add(Customer customer);

    void update(Customer customer);

    Customer findById(long id);

    List<Customer> search(CustomerSearchCondition searchCondition);
}
