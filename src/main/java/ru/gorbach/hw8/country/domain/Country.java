package ru.gorbach.hw8.country.domain;

import ru.gorbach.hw8.city.domain.City;
import ru.gorbach.hw8.common.business.domain.BaseDomain;
import ru.gorbach.hw8.order.domain.Order;

public class Country extends BaseDomain {
    private String name;
    private String language;
    private Order[] orders;
    private City[] cities;

    public Country() {
    }

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
