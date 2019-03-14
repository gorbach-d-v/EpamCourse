package ru.gorbach.hw8.country.repo.impl.memory;

import ru.gorbach.hw8.common.solutions.ArrayUtils;
import ru.gorbach.hw8.country.domain.Country;
import ru.gorbach.hw8.country.repo.CountryRepo;
import ru.gorbach.hw8.country.search.CountrySearchCondition;
import ru.gorbach.hw8.storage.sequenceGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static ru.gorbach.hw8.common.solutions.StringUtils.isNotBlank;
import static ru.gorbach.hw8.storage.Storage.countries;

public class CountryArrayRepo implements CountryRepo {
    private CountryOrderingComponent orderingComponent = new CountryOrderingComponent();
    private int countryIndex = 0;

    @Override
    public void add(Country country) {
        if (countryIndex == countries.length) {
            Country[] newArrCountries = new Country[countries.length * 2];
            System.arraycopy(countries, 0, newArrCountries, 0, countries.length);
            countries = newArrCountries;
        }

        country.setId(sequenceGenerator.generateId());
        countries[countryIndex] = country;
        countryIndex++;
    }

    @Override
    public void update(Country country) {
        // nothing to update
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
            List<Country> result = doSearch(searchCondition);

            boolean needOrdering = !result.isEmpty() && searchCondition.needOrdering();
            if (needOrdering) {
                orderingComponent.applyOrdering(result, searchCondition);
            }
            return result;
        }
    }

    private List<Country> doSearch(CountrySearchCondition searchCondition) {
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
    public void printAll() {
        for (Country country : countries) {
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
