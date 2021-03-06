package ru.gorbach.hw17.customer.repo.impl.memory;

import ru.gorbach.hw17.customer.search.CustomerSearchCondition;
import ru.gorbach.hw17.common.business.search.Paginator;
import ru.gorbach.hw17.common.solutions.utils.CollectionUtils;
import ru.gorbach.hw17.customer.domain.Customer;
import ru.gorbach.hw17.customer.repo.CustomerRepo;
import ru.gorbach.hw17.storage.SequenceGenerator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static ru.gorbach.hw17.common.solutions.utils.StringUtils.isNotBlank;
import static ru.gorbach.hw17.storage.Storage.customerList;

public class CustomerCollectionRepo implements CustomerRepo {
    private CustomerOrderingComponent orderingComponent = new CustomerOrderingComponent();

    @Override
    public Customer add(Customer customer) {
        customer.setId(SequenceGenerator.generateId());
        customerList.add(customer);
        return customer;
    }

    @Override
    public void add(Collection<Customer> customersList) {
        for (Customer customer : customersList) {
            add(customer);
        }
    }

    @Override
    public void update(Customer customer) {
        // nothing to update
    }

    @Override
    public Customer findById(Long id) {
        return findCustomerById(id);
    }

    @Override
    public List<Customer> search(CustomerSearchCondition searchCondition) {
        if (searchCondition.getId() != null) {
            return Collections.singletonList(findById(searchCondition.getId()));
        } else {
            List<Customer> result = doSearch(searchCondition);

            boolean needOrdering = !result.isEmpty() && searchCondition.needOrdering();
            if (needOrdering) {
                orderingComponent.applyOrdering(result, searchCondition);
            }
            if (!result.isEmpty() && searchCondition.needPagination()) {
                result = getPageOfData(result, searchCondition.getPaginator());
            }
            return result;
        }
    }

    @Override
    public void deleteById(Long id) {
        Customer customer = findCustomerById(id);

        if (customer != null) {
            customerList.remove(customer);
        }
    }

    @Override
    public void printAll() {
        for (Customer customer : customerList) {
            System.out.println(customer);
        }
    }

    @Override
    public List<Customer> findAll() {
        return customerList;
    }

    @Override
    public int countAll() {
        return customerList.size();
    }


    private List<Customer> doSearch(CustomerSearchCondition searchCondition) {
        boolean searchByFirstName = isNotBlank(searchCondition.getFirstName());
        boolean searchByLastName = isNotBlank(searchCondition.getLastName());

        List<Customer> result = new ArrayList<>();

        for (Customer customer : customerList) {
            if (customer != null) {
                boolean found = true;

                if (searchByFirstName) {
                    found = searchCondition.getFirstName().equals(customer.getFirstName());
                }

                if (found && searchByLastName) {
                    found = searchCondition.getLastName().equals(customer.getLastName());
                }

                if (found) {
                    result.add(customer);
                }
            }
        }
        return result;
    }

    private List<Customer> getPageOfData(List<Customer> customers, Paginator paginator) {
        return CollectionUtils.getPageOfData(customers, paginator.getLimit(), paginator.getOffset());
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
