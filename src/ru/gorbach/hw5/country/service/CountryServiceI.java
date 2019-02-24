package ru.gorbach.hw5.country.service;

import ru.gorbach.hw5.country.Country;

public interface CountryServiceI {
    void addCountry(Country country);

    Country findCountryById(long id);

    void deleteCountry(Country country);

    void deleteCountry(Long id);

    void printCountries();
}
