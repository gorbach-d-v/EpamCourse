package ru.gorbach.hw5.customer.repo;

import ru.gorbach.hw5.commons.ArrayUtils;
import ru.gorbach.hw5.customer.Customer;

import static ru.gorbach.hw5.storage.Storage.customers;

public class CustomerMemoryRepo implements CustomerRepoI{
    private int customerIndex = 0;

    public void addCustomer(Customer customer) {
        if (customerIndex == customers.length) {
            Customer[] newArrCustomers = new Customer[customers.length * 2];
            System.arraycopy(customers, 0, newArrCustomers, 0, customers.length);
            customers = newArrCustomers;
        }

        customers[customerIndex] = customer;
        customerIndex++;
    }

    public Customer findCustomerById(long id) {
        Integer customerIndex = findCustomerIndexById(id);
        if (customerIndex != null) {
            return customers[customerIndex];
        }

        return null;
    }

    public void deleteCustomer(Customer customer) {
        Integer foundIndex = findCustomerIndexByEntity(customer);

        if (foundIndex != null) {
            deleteCustomerByIndex(foundIndex);
        }
    }

    public void deleteCustomer(Long id) {
        Integer customerIndex = findCustomerIndexById(id);

        if (customerIndex != null) {
            deleteCustomerByIndex(customerIndex);
        }
    }

    public void printCustomers(){
        for(Customer customer : customers){
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

    private Integer findCustomerIndexByEntity(Customer customer) {
        for (int i = 0; i < customers.length; i++) {
            if (customers[i].equals(customer)) {
                return i;
            }
        }

        return null;
    }
}
