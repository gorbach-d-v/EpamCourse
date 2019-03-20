package ru.gorbach.hw11.country.domain;

import ru.gorbach.hw11.city.domain.City;
import ru.gorbach.hw11.common.business.domain.BaseDomain;

import java.util.List;

public abstract class Country extends BaseDomain<Long> {
    protected String name;
    protected String language;
    protected List<City> cities;
    protected CountryDiscriminator countryDiscriminator;

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

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    private String getCitiesAsString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (City city : cities) {
            stringBuilder.append(city.getName()).append(" ");
        }
        return stringBuilder.toString();
    }

    protected abstract void initDiscriminator();

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id + '\'' +
                "name='" + name + '\'' +
                ", language='" + language + '\'' +
                ", cities=" + getCitiesAsString() +
                '}';
    }
}
