package ru.gorbach.hw20.country.repo.impl.memory;

import ru.gorbach.hw20.common.business.search.Paginator;
import ru.gorbach.hw20.common.solutions.utils.CollectionUtils;
import ru.gorbach.hw20.country.domain.Country;
import ru.gorbach.hw20.country.search.CountrySearchCondition;
import ru.gorbach.hw20.country.repo.CountryRepo;
import ru.gorbach.hw20.storage.SequenceGenerator;

import java.util.*;

import static ru.gorbach.hw20.common.solutions.utils.StringUtils.isNotBlank;
import static ru.gorbach.hw20.storage.Storage.countryList;

public class CountryCollectionRepo implements CountryRepo {
    private CountryOrderingComponent orderingComponent = new CountryOrderingComponent();

    @Override
    public Country add(Country country) {
        country.setId(SequenceGenerator.generateId());
        countryList.add(country);
        return country;
    }

    @Override
    public void add(Collection<Country> countriesList) {
        for (Country country : countriesList) {
            add(country);
        }
    }

    @Override
    public void update(Country country) {
        // nothing to update
    }

    @Override
    public Optional<Country> findById(Long id) {
        return findCountryById(id);
    }

    @Override
    public List<Country> search(CountrySearchCondition searchCondition) {
        if (searchCondition.getId() != null) {
            Optional<Country> optionalCountry = findById(searchCondition.getId());
            if (optionalCountry.isPresent()) {
                return Collections.singletonList(optionalCountry.get());
            } else {
                return Collections.emptyList();
            }
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
        Optional<Country> foundOptional = findCountryById(id);
        foundOptional.map(country -> countryList.remove(country));
    }

    @Override
    public void printAll() {
        for (Country country : countryList) {
            System.out.println(country);
        }
    }

    @Override
    public List<Country> findAll() {
        return countryList;
    }

    @Override
    public int countAll() {
        return countryList.size();
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

                if (found) {
                    result.add(country);
                }
            }
        }
        return result;
    }

    private List<Country> getPageOfData(List<Country> countries, Paginator paginator) {
        return CollectionUtils.getPageOfData(countries, paginator.getLimit(), paginator.getOffset());
    }

    private Optional<Country> findCountryById(Long countryId) {
        return countryList.stream().filter(country -> Long.valueOf(countryId).equals(country.getId())).findAny();
    }
}
