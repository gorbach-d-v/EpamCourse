package ru.gorbach.hw11.customer.domain;

import ru.gorbach.hw11.common.business.domain.BaseDomain;
import ru.gorbach.hw11.order.domain.Order;

import java.util.List;

public class Customer extends BaseDomain<Long> {
    private String firstName;
    private String lastName;
    private Passport passport;
    private List<Order> orders;

    public Customer() {
    }

    public Customer(String firstName, String lastName, Passport passport) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.passport = passport;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public Passport getPassport() {
        return passport;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
