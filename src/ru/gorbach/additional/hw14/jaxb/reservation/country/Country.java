package ru.gorbach.additional.hw14.jaxb.reservation.country;


import ru.gorbach.additional.hw14.jaxb.reservation.city.City;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import java.util.List;


@XmlType(propOrder = {"name", "language", "cities"})
@XmlSeeAlso({CountryWithColdClimate.class, CountryWithHotClimate.class})
public abstract class Country {
    protected String name;
    protected String language;
    protected List<City> cities;
    protected CountryDiscriminator countryDiscriminator;

    public Country() {
        initDiscriminator();
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

    @XmlElement(name = "city")
    @XmlElementWrapper
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
                "name='" + name + '\'' +
                ", language='" + language + '\'' +
                ", cities=" + getCitiesAsString() +
                '}';
    }
}
