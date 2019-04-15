package ru.gorbach.hw22.customer.search;

import ru.gorbach.hw22.common.business.search.BaseSearchCondition;

public class CustomerSearchCondition extends BaseSearchCondition {
    private String firstName;
    private String lastName;
    private CustomerOrderByField orderByField;

    public boolean searchByFirstName() {
        return firstName == null || firstName.isEmpty();
    }

    public boolean searchByLastName() {
        return lastName == null || lastName.isEmpty();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public CustomerOrderByField getOrderByField() {
        return orderByField;
    }

    public void setOrderByField(CustomerOrderByField orderByField) {
        this.orderByField = orderByField;
    }

    public boolean needOrdering() {
        return super.needOrdering() && orderByField != null;
    }

}
