package ru.gorbach.hw5.customer.repo.impl;

import ru.gorbach.hw5.common.Solutions.ArrayUtils;
import ru.gorbach.hw5.customer.domain.BaseCustomer;
import ru.gorbach.hw5.customer.repo.CustomerRepo;
import ru.gorbach.hw5.customer.search.CustomerSearchCondition;

import static ru.gorbach.hw5.common.Solutions.StringUtils.isNotBlank;
import static ru.gorbach.hw5.storage.Storage.customers;

public class CustomerMemoryRepo implements CustomerRepo {
    private int customerIndex = 0;

    @Override
    public void add(BaseCustomer customer) {
        if (customerIndex == customers.length) {
            BaseCustomer[] newArrCustomers = new BaseCustomer[customers.length * 2];
            System.arraycopy(customers, 0, newArrCustomers, 0, customers.length);
            customers = newArrCustomers;
        }

        customers[customerIndex] = customer;
        customerIndex++;
    }

    @Override
    public BaseCustomer findById(long id) {
        Integer customerIndex = findCustomerIndexById(id);
        if (customerIndex != null) {
            return customers[customerIndex];
        }

        return null;
    }

    @Override
    public BaseCustomer[] search(CustomerSearchCondition searchCondition) {
        if (searchCondition.getId() != null) {
            return new BaseCustomer[]{findById(searchCondition.getId())};
        } else {

            boolean searchByFirstName = isNotBlank(searchCondition.getFirstName());
            boolean searchByLastName = isNotBlank(searchCondition.getLastName());



            BaseCustomer[] result = new BaseCustomer[customers.length];
            int resultIndex = 0;

            for (BaseCustomer city : customers) {
                if (city != null) {
                    boolean found = true;

                    if (searchByFirstName) {
                        found = searchCondition.getFirstName().equals(city.getFirstName());
                    }

                    if (found && searchByLastName) {
                        found = searchCondition.getLastName().equals(city.getLastName());
                    }

                    if (found && (searchByFirstName || searchByLastName)) {
                        result[resultIndex] = city;
                        resultIndex++;
                    }
                }
            }

            if (resultIndex > 0) {
                BaseCustomer toReturn[] = new BaseCustomer[resultIndex];
                System.arraycopy(result, 0, toReturn, 0, resultIndex);
                return toReturn;
            }
        }
        return new BaseCustomer[0];
    }
    
    @Override
    public void deleteById(Long id) {
        Integer customerIndex = findCustomerIndexById(id);

        if (customerIndex != null) {
            deleteCustomerByIndex(customerIndex);
        }
    }

    @Override
    public void printAll(){
        for(BaseCustomer customer : customers){
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
