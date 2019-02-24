package ru.gorbach.hw5.city;

import ru.gorbach.hw5.country.Country;
import ru.gorbach.hw5.order.Order;

public class City {
    private Long id;
    private String name;
    private int population;
    private boolean isCapital;
    private String climate;
    private Country country;
    private Order[] orders;

    private static Long counter = 0L;

    public City() {
        this.id = counter;
        counter++;
    }

    public City(String name, int population, boolean isCapital) {
        this();
        this.name = name;
        this.population = population;
        this.isCapital = isCapital;
    }

    public Long getId() {
        return id;
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

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
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

}
