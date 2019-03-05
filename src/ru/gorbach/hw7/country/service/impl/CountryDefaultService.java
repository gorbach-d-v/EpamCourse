package ru.gorbach.hw7.country.service.impl;

import ru.gorbach.hw7.country.domain.Country;
import ru.gorbach.hw7.country.repo.CountryRepo;
import ru.gorbach.hw7.country.search.CountrySearchCondition;
import ru.gorbach.hw7.country.service.CountryService;

import java.util.List;

public class CountryDefaultService implements CountryService {
    private CountryRepo countryRepo;

    @Override
    public void add(Country country) {
        countryRepo.add(country);
    }

    @Override
    public void update(Country country){
        if (country.getId() != null){
            countryRepo.update(country);
        }
    }


    @Override
    public Country findById(Long id) {
        if (id != null) {
            return countryRepo.findById(id);
        } else {
            return null;
        }
    }

    @Override
    public List<Country> search(CountrySearchCondition searchCondition){return countryRepo.search(searchCondition); }

    @Override
    public void deleteById(Long id) {
        if (id != null){
            countryRepo.deleteById(id);
        }
    }

    @Override
    public void delete(Country country) {
        if (country.getId() != null){
            countryRepo.deleteById(country.getId());
        }
    }

    @Override
    public void printAll() {
        countryRepo.printAll();
    }
}
