package ru.gorbach.hw4.customer;

import ru.gorbach.hw4.Passport;
import ru.gorbach.hw4.order.Order;

public class Customer {
    private Long id;
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

    public Long getId() {
        return id;
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
