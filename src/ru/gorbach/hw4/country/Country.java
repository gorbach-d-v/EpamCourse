package ru.gorbach.hw4.country;

import ru.gorbach.hw4.city.City;
import ru.gorbach.hw4.order.Order;

public class Country {
    private String name;
    private String language;
    private Order[] orders;
    private City[] cities;

    public Country(String name, String language) {
        this.name = name;
        this.language = language;
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
