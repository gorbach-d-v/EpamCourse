package ru.gorbach.hw14.customer.repo.impl.memory;

import ru.gorbach.hw14.customer.domain.Customer;
import ru.gorbach.hw14.customer.search.CustomerOrderByField;

import java.util.*;

import static ru.gorbach.hw14.common.business.repo.memory.CommonComparatorHolder.getComparatorForNullableStrings;
import static ru.gorbach.hw14.customer.search.CustomerOrderByField.FIRSTNAME;
import static ru.gorbach.hw14.customer.search.CustomerOrderByField.LASTNAME;

public class CustomerComparatorComponent {
    private static final CustomerComparatorComponent INSTANCE = new CustomerComparatorComponent();
    private static Map<CustomerOrderByField, Comparator<Customer>> comparatorsByField = new HashMap<>();
    /**
     * For complex comparator only
     */
    private static Set<CustomerOrderByField> fieldComparePriorityOrder = new LinkedHashSet<>(Arrays.asList(FIRSTNAME, LASTNAME));

    static {

        comparatorsByField.put(FIRSTNAME, getComparatorForFirstNameField());
        comparatorsByField.put(LASTNAME, getComparatorForLastNameField());
    }

    private CustomerComparatorComponent() {
    }


    public static CustomerComparatorComponent getInstance() {
        return INSTANCE;
    }

    private static Comparator<Customer> getComparatorForFirstNameField() {
        return new Comparator<Customer>() {
            @Override
            public int compare(Customer customer1, Customer customer2) {
                return getComparatorForNullableStrings().compare(customer1.getFirstName(), customer2.getLastName());
            }
        };
    }

    private static Comparator<Customer> getComparatorForLastNameField() {
        return new Comparator<Customer>() {
            @Override
            public int compare(Customer customer1, Customer customer2) {
                return getComparatorForNullableStrings().compare(customer1.getLastName(), customer2.getLastName());
            }
        };
    }

    public Comparator<Customer> getComparatorForField(CustomerOrderByField field) {
        return comparatorsByField.get(field);
    }

    public Comparator<Customer> getComplexComparator(CustomerOrderByField field) {
        return new Comparator<Customer>() {

            @Override
            public int compare(Customer m1, Customer m2) {
                int result = 0;
                Comparator<Customer> customerComparator = comparatorsByField.get(field);

                if (customerComparator != null) {
                    result = customerComparator.compare(m1, m2);
                    if (result == 0) {
                        for (CustomerOrderByField otherField : fieldComparePriorityOrder) {
                            if (!otherField.equals(field)) {
                                result = comparatorsByField.get(otherField).compare(m1, m2);
                                if (result != 0) {
                                    break;
                                }
                            }
                        }

                    }
                }


                return result;
            }
        };
    }
}
