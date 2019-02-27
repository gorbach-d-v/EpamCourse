package ru.gorbach.hw6.customer.domain;

import ru.gorbach.hw6.common.business.domain.BaseDomain;
import ru.gorbach.hw6.order.domain.Order;

public class Customer extends BaseDomain {
    private String firstName;
    private String lastName;
    private Passport passport;
    private Order[] orders;

    private static Long counter = 0L;

    public Customer() {
        this.id = counter;
        counter++;
    }

    public Customer(String firstName, String lastName, Passport passport) {
        this();
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
