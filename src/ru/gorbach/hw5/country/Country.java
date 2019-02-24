package ru.gorbach.hw5.country;

import ru.gorbach.hw5.city.City;
import ru.gorbach.hw5.order.Order;

public class Country {
    private Long id;
    private String name;
    private String language;
    private Order[] orders;
    private City[] cities;

    private static Long counter = 0L;

    public Country() {
        this.id = counter;
        counter++;
    }

    public Country(String name, String language) {
        this();
        this.name = name;
        this.language = language;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLanguage() {
        return language;
    }

    public Order[] getOrders() {
        return orders;
    }

    public void setOrders(Order[] orders) {
        this.orders = orders;
    }

    public City[] getCities() {
        return cities;
    }

    public void setCities(City[] cities) {
        this.cities = cities;
    }
}
