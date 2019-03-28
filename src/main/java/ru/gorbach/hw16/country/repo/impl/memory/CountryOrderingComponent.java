package ru.gorbach.hw16.country.repo.impl.memory;

import ru.gorbach.hw16.country.domain.Country;
import ru.gorbach.hw16.country.search.CountrySearchCondition;
import ru.gorbach.hw16.country.search.CountryOrderByField;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CountryOrderingComponent {
    public void applyOrdering(List<Country> countries, CountrySearchCondition countrySearchCondition) {
        Comparator<Country> countryComparator = null;

        CountryOrderByField field = countrySearchCondition.getOrderByField();
        switch (countrySearchCondition.getOrderType()) {

            case SIMPLE: {
                countryComparator = CountryComparatorComponent.getInstance().getComparatorForField(field);
                break;
            }
            case COMPLEX: {
                countryComparator = CountryComparatorComponent.getInstance().getComplexComparator(field);
                break;
            }
        }

        if (countryComparator != null) {
            switch (countrySearchCondition.getOrderDirection()) {

                case ASC:
                    Collections.sort(countries, countryComparator);
                    break;
                case DESC:
                    Collections.sort(countries, countryComparator.reversed());
                    break;
            }
        }
    }
}
