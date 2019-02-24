package ru.gorbach.hw5.country.service;

import ru.gorbach.hw5.country.repo.CountryMemoryRepo;
import ru.gorbach.hw5.country.Country;

public class CountryMemoryService implements CountryServiceI{
    private CountryMemoryRepo countryRepo = new CountryMemoryRepo();

    public void addCountry(Country country) {
        countryRepo.addCountry(country);
    }

    public Country findCountryById(long id) {
        return countryRepo.findCountryById(id);
    }

    public void deleteCountry(Country country) {
        countryRepo.deleteCountry(country);
    }

    public void deleteCountry(Long id) {
        countryRepo.deleteCountry(id);
    }

    public void printCountries() {
        countryRepo.printCountries();
    }
}
