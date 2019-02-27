package ru.gorbach.hw6.country.domain;

import ru.gorbach.hw6.city.domain.City;
import ru.gorbach.hw6.common.business.domain.BaseDomain;
import ru.gorbach.hw6.order.domain.Order;

public class Country extends BaseDomain {
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
