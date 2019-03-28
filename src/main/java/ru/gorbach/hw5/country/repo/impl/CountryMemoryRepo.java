package ru.gorbach.hw5.country.repo.impl;

import ru.gorbach.hw5.common.Solutions.ArrayUtils;
import ru.gorbach.hw5.country.domain.Country;
import ru.gorbach.hw5.country.repo.CountryRepo;
import ru.gorbach.hw5.country.search.CountrySearchCondition;

import static ru.gorbach.hw5.common.Solutions.StringUtils.isNotBlank;
import static ru.gorbach.hw5.storage.Storage.countries;

public class CountryMemoryRepo implements CountryRepo {
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
    public Country[] search(CountrySearchCondition searchCondition) {
        if (searchCondition.getId() != null) {
            return new Country[]{findById(searchCondition.getId())};
        } else {

            boolean searchByName = isNotBlank(searchCondition.getName());
            boolean searchByLanguage = isNotBlank(searchCondition.getLanguage());



            Country[] result = new Country[countries.length];
            int resultIndex = 0;

            for (Country city : countries) {
                if (city != null) {
                    boolean found = true;

                    if (searchByName) {
                        found = searchCondition.getName().equals(city.getName());
                    }

                    if (found && searchByLanguage) {
                        found = searchCondition.getLanguage().equals(city.getLanguage());
                    }

                    if (found && (searchByName || searchByLanguage)) {
                        result[resultIndex] = city;
                        resultIndex++;
                    }
                }
            }

            if (resultIndex > 0) {
                Country toReturn[] = new Country[resultIndex];
                System.arraycopy(result, 0, toReturn, 0, resultIndex);
                return toReturn;
            }
        }
        return new Country[0];
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
