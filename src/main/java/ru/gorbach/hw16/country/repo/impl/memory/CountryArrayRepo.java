package ru.gorbach.hw16.country.repo.impl.memory;

import ru.gorbach.hw16.common.business.search.Paginator;
import ru.gorbach.hw16.common.solutions.utils.ArrayUtils;
import ru.gorbach.hw16.common.solutions.utils.CollectionUtils;
import ru.gorbach.hw16.country.domain.Country;
import ru.gorbach.hw16.country.repo.CountryRepo;
import ru.gorbach.hw16.country.search.CountrySearchCondition;
import ru.gorbach.hw16.storage.SequenceGenerator;

import java.util.*;

import static ru.gorbach.hw16.common.solutions.utils.StringUtils.isNotBlank;
import static ru.gorbach.hw16.storage.Storage.countries;

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

        country.setId(SequenceGenerator.generateId());
        countries[countryIndex] = country;
        countryIndex++;
    }

    @Override
    public void add(Collection<Country> countriesList) {
        for (Country country : countriesList){
            add(country);
        }
    }

    @Override
    public void update(Country country) {
        // nothing to update
    }

    @Override
    public Country findById(Long id) {
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
            if (!result.isEmpty() && searchCondition.needPagination()) {
                result = getPageOfData(result, searchCondition.getPaginator());
            }
            return result;
        }
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

    @Override
    public List<Country> findAll() {
        return new ArrayList<>(Arrays.asList(countries));
    }

    @Override
    public int countAll() {
        return countries.length;
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

                if (found) {
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

    private List<Country> getPageOfData(List<Country> countries, Paginator paginator) {
        return CollectionUtils.getPageOfData(countries, paginator.getLimit(), paginator.getOffset());
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
