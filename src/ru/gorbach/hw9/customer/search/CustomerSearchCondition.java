package ru.gorbach.hw9.customer.search;

import ru.gorbach.hw9.common.business.search.BaseSearchCondition;

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
