package ru.gorbach.hw5.order.domain;

import ru.gorbach.hw5.city.domain.City;
import ru.gorbach.hw5.common.business.domain.BaseDomain;
import ru.gorbach.hw5.country.domain.Country;
import ru.gorbach.hw5.customer.domain.BaseCustomer;

public class Order extends BaseDomain {
    private int price;
    private BaseCustomer customer;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public BaseCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(BaseCustomer customer) {
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
