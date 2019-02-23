package ru.gorbach;

public class Customer {
    private String firstName;
    private String lastName;
    private Passport passport;

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
}
