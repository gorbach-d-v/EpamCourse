package ru.gorbach.hw5.order;

import ru.gorbach.hw5.city.City;
import ru.gorbach.hw5.country.Country;
import ru.gorbach.hw5.customer.Customer;

public class Order {
    private Long id;
    private int price;
    private Customer customer;
    private Country country;
    private City city;

    private static Long counter = 1L;

    public Order() {
        this.id = counter;
        counter++;
    }

    public Order(int price) {
        this();
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", price=" + price +
                '}';
    }
}
