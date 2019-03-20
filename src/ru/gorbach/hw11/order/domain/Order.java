package ru.gorbach.hw11.order.domain;

import ru.gorbach.hw11.city.domain.City;
import ru.gorbach.hw11.common.business.domain.BaseDomain;
import ru.gorbach.hw11.country.domain.Country;
import ru.gorbach.hw11.customer.domain.Customer;

public class Order extends BaseDomain<Long> {
    private int price;
    private Customer customer;
    private Country country;
    private City city;

    public Order() {
    }

    public Order(int price) {
        this.price = price;
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
//        return "Order{" +
//                "id=" + id +
//                ", price=" + price +
//                '}';
        StringBuilder str = new StringBuilder();
        str.append("Order " + id + ": ");
        str.append("Customer - ");
        str.append((customer != null) ? customer.getFirstName() + " " + customer.getLastName() : "no info");
        str.append("; Country - ");
        str.append((country != null) ? country.getName() : "no info");
        str.append("; City - ");
        str.append((city != null) ? city.getName() : "no info");
        return str.toString();
    }
}
