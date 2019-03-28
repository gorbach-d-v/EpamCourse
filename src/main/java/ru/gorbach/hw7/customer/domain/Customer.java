package ru.gorbach.hw7.customer.domain;

import ru.gorbach.hw7.common.business.domain.BaseDomain;
import ru.gorbach.hw7.order.domain.Order;

public class Customer extends BaseDomain {
    private String firstName;
    private String lastName;
    private Passport passport;
    private Order[] orders;

    public Customer() {
    }

    public Customer(String firstName, String lastName, Passport passport) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.passport = passport;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Passport getPassport() {
        return passport;
    }

    public Order[] getOrders() {
        return orders;
    }

    public void setOrders(Order[] orders) {
        this.orders = orders;
    }
}
