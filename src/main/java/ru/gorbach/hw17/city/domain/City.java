package ru.gorbach.hw17.city.domain;

import ru.gorbach.hw17.common.business.domain.BaseDomain;
import ru.gorbach.hw17.country.domain.Country;
import ru.gorbach.hw17.order.domain.Order;

import java.util.List;

public class City extends BaseDomain<Long> {
    private String name;
    private int population;
    private boolean isCapital;
    private Long countryId;
    private List<Order> orders;

    public City() {
    }

    public City(String name, int population, boolean isCapital) {
        this.name = name;
        this.population = population;
        this.isCapital = isCapital;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public boolean isCapital() {
        return isCapital;
    }

    public void setCapital(boolean capital) {
        isCapital = capital;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id + '\'' +
                ", name='" + name +
                ", population=" + population +
                ", isCapital=" + isCapital +
                '}';
    }
}
