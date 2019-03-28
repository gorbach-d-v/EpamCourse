package ru.gorbach.hw8.city.domain;

import ru.gorbach.hw8.common.business.domain.BaseDomain;
import ru.gorbach.hw8.country.domain.Country;
import ru.gorbach.hw8.order.domain.Order;

public class City extends BaseDomain {
    private String name;
    private int population;
    private boolean isCapital;
    private Climate climate;
    private Country country;
    private Order[] orders;

    public City() {
    }

    public City(String name, int population, boolean isCapital) {
        this.name = name;
        this.population = population;
        this.isCapital = isCapital;
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

    public Climate getClimate() {
        return climate;
    }

    public void setClimate(Climate climate) {
        this.climate = climate;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Order[] getOrders() {
        return orders;
    }

    public void setOrders(Order[] orders) {
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
