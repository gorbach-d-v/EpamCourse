package ru.gorbach.hw7.customer.repo.impl;

import ru.gorbach.hw7.customer.domain.Customer;
import ru.gorbach.hw7.customer.repo.CustomerRepo;
import ru.gorbach.hw7.customer.search.CustomerSearchCondition;
import ru.gorbach.hw7.storage.sequenceGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ru.gorbach.hw7.common.solutions.StringUtils.isNotBlank;
import static ru.gorbach.hw7.storage.Storage.customerList;

public class CustomerMemoryListRepo implements CustomerRepo {
    private int customerIndex = 0;

    @Override
    public void add(Customer customer) {
        customer.setId(sequenceGenerator.generateId());
        customerList.add(customer);
    }

    @Override
    public void update(Customer customer) {
        // nothing to update
    }

    @Override
    public Customer findById(long id) {
        return findCustomerById(id);
    }

    @Override
    public List<Customer> search(CustomerSearchCondition searchCondition) {
        if (searchCondition.getId() != null) {
            return Collections.singletonList(findById(searchCondition.getId()));
        } else {

            boolean searchByFirstName = isNotBlank(searchCondition.getFirstName());
            boolean searchByLastName = isNotBlank(searchCondition.getLastName());

            List <Customer> result = new ArrayList<>();

            for (Customer customer : customerList) {
                if (customer != null) {
                    boolean found = true;

                    if (searchByFirstName) {
                        found = searchCondition.getFirstName().equals(customer.getFirstName());
                    }

                    if (found && searchByLastName) {
                        found = searchCondition.getLastName().equals(customer.getLastName());
                    }

                    if (found && (searchByFirstName || searchByLastName)) {
                        result.add(customer);
                    }
                }
            }
            return result;
        }
    }

    @Override
    public void deleteById(Long id) {
        Customer customer = findCustomerById(id);

        if (customer != null){
            customerList.remove(customer);
        }
    }

    @Override
    public void printAll(){
        for(Customer customer : customerList){
            System.out.println(customer);
        }
    }

    private Customer findCustomerById(Long customerId) {
        for (Customer customer : customerList) {
            if (Long.valueOf(customerId).equals(customer.getId())) {
                return customer;
            }
        }
        return null;
    }
}
