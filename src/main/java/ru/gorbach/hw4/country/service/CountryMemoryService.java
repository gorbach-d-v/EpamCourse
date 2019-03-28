package ru.gorbach.hw4.country.service;

import ru.gorbach.hw4.country.Country;
import ru.gorbach.hw4.country.repo.CountryMemoryRepo;

public class CountryMemoryService {
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

    public void printCountrys() {
        countryRepo.printCountrys();
    }
}
