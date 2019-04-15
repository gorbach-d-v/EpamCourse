package ru.gorbach.hw22.country.domain;

import ru.gorbach.hw22.city.domain.City;

import java.util.List;

public class ProxyCountry extends Country {
    private static final String ERROR_MESSAGE = "You deal with proxy! All operations not supported, nut get/set Id";

    public ProxyCountry(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException(ERROR_MESSAGE);
    }

    @Override
    public void setName(String name) {
        throw new UnsupportedOperationException(ERROR_MESSAGE);
    }

    @Override
    public String getLanguage() {
        throw new UnsupportedOperationException(ERROR_MESSAGE);
    }

    @Override
    public void setLanguage(String language) {
        throw new UnsupportedOperationException(ERROR_MESSAGE);
    }

    @Override
    public List<City> getCities() {
        throw new UnsupportedOperationException(ERROR_MESSAGE);
    }

    @Override
    public void setCities(List<City> cities) {
        throw new UnsupportedOperationException(ERROR_MESSAGE);
    }

    @Override
    public CountryDiscriminator getDiscriminator() {
        throw new UnsupportedOperationException(ERROR_MESSAGE);
    }

    @Override
    public String toString() {
        return "ID " + id;
    }

    @Override
    protected void initDiscriminator() {
    }
}
