package ru.gorbach.hw5.customer.search;

import ru.gorbach.hw5.common.business.search.BaseSearchCondition;

public class CustomerSearchCondition extends BaseSearchCondition {
    private String firstName;
    private String lastName;

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
}
