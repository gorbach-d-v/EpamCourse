package ru.gorbach.hw14.customer.repo.impl.memory;

import ru.gorbach.hw14.common.business.search.Paginator;
import ru.gorbach.hw14.common.solutions.utils.ArrayUtils;
import ru.gorbach.hw14.common.solutions.utils.CollectionUtils;
import ru.gorbach.hw14.customer.domain.Customer;
import ru.gorbach.hw14.customer.repo.CustomerRepo;
import ru.gorbach.hw14.customer.search.CustomerSearchCondition;
import ru.gorbach.hw14.storage.SequenceGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static ru.gorbach.hw14.common.solutions.utils.StringUtils.isNotBlank;
import static ru.gorbach.hw14.storage.Storage.customers;

public class CustomerArrayRepo implements CustomerRepo {
    private CustomerOrderingComponent orderingComponent = new CustomerOrderingComponent();

    private int customerIndex = 0;

    @Override
    public void add(Customer customer) {
        if (customerIndex == customers.length) {
            Customer[] newArrCustomers = new Customer[customers.length * 2];
            System.arraycopy(customers, 0, newArrCustomers, 0, customers.length);
            customers = newArrCustomers;
        }

        customer.setId(SequenceGenerator.generateId());
        customers[customerIndex] = customer;
        customerIndex++;
    }

    @Override
    public void update(Customer customer) {
        // nothing to update
    }

    @Override
    public Customer findById(Long id) {
        Integer customerIndex = findCustomerIndexById(id);
        if (customerIndex != null) {
            return customers[customerIndex];
        }

        return null;
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

    private List<Customer> doSearch(CustomerSearchCondition searchCondition) {
        boolean searchByFirstName = isNotBlank(searchCondition.getFirstName());
        boolean searchByLastName = isNotBlank(searchCondition.getLastName());


        Customer[] result = new Customer[customers.length];
        int resultIndex = 0;

        for (Customer customer : customers) {
            if (customer != null) {
                boolean found = true;

                if (searchByFirstName) {
                    found = searchCondition.getFirstName().equals(customer.getFirstName());
                }

                if (found && searchByLastName) {
                    found = searchCondition.getLastName().equals(customer.getLastName());
                }

                if (found) {
                    result[resultIndex] = customer;
                    resultIndex++;
                }
            }
        }

        if (resultIndex > 0) {
            Customer toReturn[] = new Customer[resultIndex];
            System.arraycopy(result, 0, toReturn, 0, resultIndex);
            return new ArrayList<>(Arrays.asList(toReturn));
        }
        return Collections.emptyList();
    }

    private List<Customer> getPageOfData(List<Customer> customers, Paginator paginator) {
        return CollectionUtils.getPageOfData(customers, paginator.getLimit(), paginator.getOffset());
    }

    @Override
    public void deleteById(Long id) {
        Integer customerIndex = findCustomerIndexById(id);

        if (customerIndex != null) {
            deleteCustomerByIndex(customerIndex);
        }
    }

    @Override
    public void printAll() {
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    private void deleteCustomerByIndex(int index) {
        ArrayUtils.removeElement(customers, index);
        customerIndex--;
    }

    private Integer findCustomerIndexById(Long customerId) {
        for (int i = 0; i < customers.length; i++) {
            if (customers[i].getId().equals(customerId)) {
                return i;
            }
        }
        return null;
    }
}
