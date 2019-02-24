package ru.gorbach.hw5.country.repo;

import ru.gorbach.hw5.country.Country;

public interface CountryRepoI {
    void addCountry(Country country);

    Country findCountryById(long id);

    void deleteCountry(Country country);

    void deleteCountry(Long id);

    void printCountries();
}
