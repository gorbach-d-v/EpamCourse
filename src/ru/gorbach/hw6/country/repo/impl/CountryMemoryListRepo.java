package ru.gorbach.hw6.country.repo.impl;

import ru.gorbach.hw6.country.domain.Country;
import ru.gorbach.hw6.country.repo.CountryRepo;
import ru.gorbach.hw6.country.search.CountrySearchCondition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ru.gorbach.hw6.common.Solutions.StringUtils.isNotBlank;
import static ru.gorbach.hw6.storage.Storage.countryList;

public class CountryMemoryListRepo implements CountryRepo {
    private int countryIndex = 0;

    @Override
    public void add(Country country) {
        countryList.add(country);
    }

    @Override
    public Country findById(long id) {
        return findCountryById(id);
    }

    @Override
    public List<Country> search(CountrySearchCondition searchCondition) {
        if (searchCondition.getId() != null) {
            return Collections.singletonList(findById(searchCondition.getId()));
        } else {

            boolean searchByName = isNotBlank(searchCondition.getName());
            boolean searchByLanguage = isNotBlank(searchCondition.getLanguage());

            List<Country> result = new ArrayList<>();

            for (Country country : countryList) {
                if (country != null) {
                    boolean found = true;

                    if (searchByName) {
                        found = searchCondition.getName().equals(country.getName());
                    }

                    if (found && searchByLanguage) {
                        found = searchCondition.getLanguage().equals(country.getLanguage());
                    }

                    if (found && (searchByName || searchByLanguage)) {
                        result.add(country);
                    }
                }
            }
            return result;
        }
    }

    @Override
    public void deleteById(Long id) {
        Country country = findCountryById(id);

        if (country != null){
            countryList.remove(country);
        }
    }

    @Override
    public void printAll(){
        for(Country country : countryList){
            System.out.println(country);
        }
    }

    private Country findCountryById(Long countryId) {
        for (Country country : countryList) {
            if (Long.valueOf(countryId).equals(country.getId())) {
                return country;
            }
        }
        return null;
    }
}
