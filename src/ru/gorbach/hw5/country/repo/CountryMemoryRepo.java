package ru.gorbach.hw5.country.repo;

import ru.gorbach.hw5.commons.ArrayUtils;
import ru.gorbach.hw5.country.Country;

import static ru.gorbach.hw5.storage.Storage.countries;

public class CountryMemoryRepo implements CountryRepoI {
    private int countryIndex = 0;

    public void addCountry(Country country) {
        if (countryIndex == countries.length) {
            Country[] newArrCountries = new Country[countries.length * 2];
            System.arraycopy(countries, 0, newArrCountries, 0, countries.length);
            countries = newArrCountries;
        }

        countries[countryIndex] = country;
        countryIndex++;
    }

    public Country findCountryById(long id) {
        Integer countryIndex = findCountryIndexById(id);
        if (countryIndex != null) {
            return countries[countryIndex];
        }

        return null;
    }

    public void deleteCountry(Country country) {
        Integer foundIndex = findCountryIndexByEntity(country);

        if (foundIndex != null) {
            deleteCountryByIndex(foundIndex);
        }
    }

    public void deleteCountry(Long id) {
        Integer countryIndex = findCountryIndexById(id);

        if (countryIndex != null) {
            deleteCountryByIndex(countryIndex);
        }
    }

    public void printCountries(){
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

    private Integer findCountryIndexByEntity(Country country) {
        for (int i = 0; i < countries.length; i++) {
            if (countries[i].equals(country)) {
                return i;
            }
        }

        return null;
    }
}
