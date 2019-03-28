package ru.gorbach.hw6.country.repo.impl;

import ru.gorbach.hw6.country.domain.Country;
import ru.gorbach.hw6.country.search.CountrySearchCondition;
import ru.gorbach.hw6.common.Solutions.ArrayUtils;
import ru.gorbach.hw6.country.repo.CountryRepo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static ru.gorbach.hw6.common.Solutions.StringUtils.isNotBlank;
import static ru.gorbach.hw6.storage.Storage.countries;

public class CountryMemoryArrayRepo implements CountryRepo {
    private int countryIndex = 0;

    @Override
    public void add(Country country) {
        if (countryIndex == countries.length) {
            Country[] newArrCountries = new Country[countries.length * 2];
            System.arraycopy(countries, 0, newArrCountries, 0, countries.length);
            countries = newArrCountries;
        }

        countries[countryIndex] = country;
        countryIndex++;
    }

    @Override
    public Country findById(long id) {
        Integer countryIndex = findCountryIndexById(id);
        if (countryIndex != null) {
            return countries[countryIndex];
        }

        return null;
    }

    @Override
    public List<Country> search(CountrySearchCondition searchCondition) {
        if (searchCondition.getId() != null) {
            return Collections.singletonList(findById(searchCondition.getId()));
        } else {

            boolean searchByName = isNotBlank(searchCondition.getName());
            boolean searchByLanguage = isNotBlank(searchCondition.getLanguage());



            Country[] result = new Country[countries.length];
            int resultIndex = 0;

            for (Country country : countries) {
                if (country != null) {
                    boolean found = true;

                    if (searchByName) {
                        found = searchCondition.getName().equals(country.getName());
                    }

                    if (found && searchByLanguage) {
                        found = searchCondition.getLanguage().equals(country.getLanguage());
                    }

                    if (found && (searchByName || searchByLanguage)) {
                        result[resultIndex] = country;
                        resultIndex++;
                    }
                }
            }

            if (resultIndex > 0) {
                Country toReturn[] = new Country[resultIndex];
                System.arraycopy(result, 0, toReturn, 0, resultIndex);
                return new ArrayList<>(Arrays.asList(toReturn));
            }
        }
        return Collections.emptyList();
    }
    
    @Override
    public void deleteById(Long id) {
        Integer countryIndex = findCountryIndexById(id);

        if (countryIndex != null) {
            deleteCountryByIndex(countryIndex);
        }
    }

    @Override
    public void printAll(){
        for(Country country : countries){
            System.out.println(country);
        }
    }


    private void deleteCountryByIndex(int index) {
        ArrayUtils.removeElement(countries, index);
        countryIndex--;
    }

    private Integer findCountryIndexById(Long countryId) {
        for (int i = 0; i < countries.length; i++) {
            if (countries[i].getId().equals(countryId)) {
                return i;
            }
        }
        return null;
    }
}
