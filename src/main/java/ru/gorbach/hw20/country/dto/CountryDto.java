package ru.gorbach.hw20.country.dto;

import ru.gorbach.hw20.city.dto.CityDto;
import ru.gorbach.hw20.common.business.dto.BaseDto;
import ru.gorbach.hw20.country.domain.CountryDiscriminator;

import java.util.List;

public abstract class CountryDto extends BaseDto {
    protected String name;
    protected String language;
    protected List<CityDto> cities;
    protected CountryDiscriminator countryDiscriminator;

    public CountryDto() {
        initDiscriminator();
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

    public List<CityDto> getCities() {
        return cities;
    }

    public void setCities(List<CityDto> cities) {
        this.cities = cities;
    }

    private String getCitiesAsString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (CityDto city : cities) {
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
