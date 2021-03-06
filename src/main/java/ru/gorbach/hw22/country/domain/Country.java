package ru.gorbach.hw22.country.domain;

import ru.gorbach.hw22.city.domain.City;
import ru.gorbach.hw22.common.business.domain.BaseDomain;

import java.util.List;

public abstract class Country extends BaseDomain<Long> {
    protected String name;
    protected String language;
    protected List<City> cities;
    protected CountryDiscriminator countryDiscriminator;

    public Country() {
        initDiscriminator();
    }

    public Country(Long id) {
        this.id = id;
    }

    public Country(String name, String language) {
        this();
        this.name = name;
        this.language = language;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLanguage(String language) {
        this.language = language;
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

    public CountryDiscriminator getDiscriminator() {
        return countryDiscriminator;
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
               /* ", cities=" + getCitiesAsString() +*/
                '}';
    }
}
