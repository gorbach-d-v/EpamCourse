package ru.gorbach.hw19.customer.repo.impl.memory;

import ru.gorbach.hw19.common.business.search.Paginator;
import ru.gorbach.hw19.common.solutions.utils.ArrayUtils;
import ru.gorbach.hw19.common.solutions.utils.CollectionUtils;
import ru.gorbach.hw19.customer.domain.Customer;
import ru.gorbach.hw19.customer.repo.CustomerRepo;
import ru.gorbach.hw19.customer.search.CustomerSearchCondition;
import ru.gorbach.hw19.storage.SequenceGenerator;

import java.util.*;
import java.util.stream.IntStream;

import static ru.gorbach.hw19.common.solutions.utils.StringUtils.isNotBlank;
import static ru.gorbach.hw19.storage.Storage.customers;

public class CustomerArrayRepo implements CustomerRepo {
    private CustomerOrderingComponent orderingComponent = new CustomerOrderingComponent();

    private int customerIndex = 0;

    @Override
    public Customer add(Customer customer) {
        if (customerIndex == customers.length) {
            Customer[] newArrCustomers = new Customer[customers.length * 2];
            System.arraycopy(customers, 0, newArrCustomers, 0, customers.length);
            customers = newArrCustomers;
        }

        customer.setId(SequenceGenerator.generateId());
        customers[customerIndex] = customer;
        customerIndex++;
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
    public Optional<Customer> findById(Long id) {
        return findCustomerIndexById(id).map(index -> customers[index]);
    }

    @Override
    public List<Customer> search(CustomerSearchCondition searchCondition) {
        if (searchCondition.getId() != null) {
            Optional<Customer> optionalCountry = findById(searchCondition.getId());
            if (optionalCountry.isPresent()) {
                return Collections.singletonList(optionalCountry.get());
            } else {
                return Collections.emptyList();
            }
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
        findCustomerIndexById(id).ifPresent(this::deleteCustomerByIndex);
    }

    @Override
    public void printAll() {
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(Arrays.asList(customers));
    }

    @Override
    public int countAll() {
        return customers.length;
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

    private void deleteCustomerByIndex(int index) {
        ArrayUtils.removeElement(customers, index);
        customerIndex--;
    }

    private Optional<Integer> findCustomerIndexById(Long customerId) {
        OptionalInt optionalInt = IntStream.range(0, customers.length).filter(index ->
                Long.valueOf(customerId).equals(customers[index].getId())
        ).findAny();

        return optionalInt.isPresent() ? Optional.of(optionalInt.getAsInt()) : Optional.empty();
    }
}
