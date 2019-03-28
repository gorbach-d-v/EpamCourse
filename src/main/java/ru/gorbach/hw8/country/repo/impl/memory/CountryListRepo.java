package ru.gorbach.hw8.country.repo.impl.memory;

import ru.gorbach.hw8.country.domain.Country;
import ru.gorbach.hw8.country.repo.CountryRepo;
import ru.gorbach.hw8.country.search.CountrySearchCondition;
import ru.gorbach.hw8.storage.sequenceGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ru.gorbach.hw8.common.solutions.StringUtils.isNotBlank;
import static ru.gorbach.hw8.storage.Storage.countryList;

public class CountryListRepo implements CountryRepo {
    private CountryOrderingComponent orderingComponent = new CountryOrderingComponent();

    @Override
    public void add(Country country) {
        country.setId(sequenceGenerator.generateId());
        countryList.add(country);
    }

    @Override
    public void update(Country country) {
        // nothing to update
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

    @Override
    public void deleteById(Long id) {
        Country country = findCountryById(id);

        if (country != null) {
            countryList.remove(country);
        }
    }

    @Override
    public void printAll() {
        for (Country country : countryList) {
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
