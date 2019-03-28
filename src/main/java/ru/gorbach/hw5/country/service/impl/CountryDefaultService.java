package ru.gorbach.hw5.country.service.impl;

import ru.gorbach.hw5.country.domain.Country;
import ru.gorbach.hw5.country.repo.CountryRepo;
import ru.gorbach.hw5.country.search.CountrySearchCondition;
import ru.gorbach.hw5.country.service.CountryService;

public class CountryDefaultService implements CountryService {
    private CountryRepo countryRepo;

    @Override
    public void add(Country country) {
        countryRepo.add(country);
    }

    @Override
    public Country findById(long id) {
        return countryRepo.findById(id);
    }

    @Override
    public Country[] search(CountrySearchCondition searchCondition){return countryRepo.search(searchCondition); }

    @Override
    public void deleteById(Long id) {
        countryRepo.deleteById(id);
    }

    @Override
    public void printAll() {
        countryRepo.printAll();
    }
}
