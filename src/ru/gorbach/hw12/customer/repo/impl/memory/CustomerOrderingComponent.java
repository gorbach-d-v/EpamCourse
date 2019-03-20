package ru.gorbach.hw12.customer.repo.impl.memory;

import ru.gorbach.hw12.customer.domain.Customer;
import ru.gorbach.hw12.customer.search.CustomerOrderByField;
import ru.gorbach.hw12.customer.search.CustomerSearchCondition;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CustomerOrderingComponent {
    public void applyOrdering(List<Customer> customers, CustomerSearchCondition customerSearchCondition) {
        Comparator<Customer> customerComparator = null;

        CustomerOrderByField field = customerSearchCondition.getOrderByField();
        switch (customerSearchCondition.getOrderType()) {

            case SIMPLE: {
                customerComparator = CustomerComparatorComponent.getInstance().getComparatorForField(field);
                break;
            }
            case COMPLEX: {
                customerComparator = CustomerComparatorComponent.getInstance().getComplexComparator(field);
                break;
            }
        }

        if (customerComparator != null) {
            switch (customerSearchCondition.getOrderDirection()) {

                case ASC:
                    Collections.sort(customers, customerComparator);
                    break;
                case DESC:
                    Collections.sort(customers, customerComparator.reversed());
                    break;
            }
        }
    }
}
