package ru.gorbach.hw5.customer.domain;

import ru.gorbach.hw5.common.business.domain.BaseDomain;
import ru.gorbach.hw5.customer.Passport;
import ru.gorbach.hw5.order.domain.Order;

public class BaseCustomer extends BaseDomain {
    private String firstName;
    private String lastName;
    private Passport passport;
    private Order[] orders;

    private static Long counter = 0L;

    public BaseCustomer() {
        this.id = counter;
        counter++;
    }

    public BaseCustomer(String firstName, String lastName, Passport passport) {
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
