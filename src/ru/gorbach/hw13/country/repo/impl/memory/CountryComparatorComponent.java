package ru.gorbach.hw13.country.repo.impl.memory;

import ru.gorbach.hw13.country.domain.Country;
import ru.gorbach.hw13.country.search.CountryOrderByField;

import java.util.*;

import static ru.gorbach.hw13.common.business.repo.memory.CommonComparatorHolder.getComparatorForNullableStrings;
import static ru.gorbach.hw13.country.search.CountryOrderByField.LANGUAGE;
import static ru.gorbach.hw13.country.search.CountryOrderByField.NAME;

public class CountryComparatorComponent {
    private static final CountryComparatorComponent INSTANCE = new CountryComparatorComponent();
    private static Map<CountryOrderByField, Comparator<Country>> comparatorsByField = new HashMap<>();
    /**
     * For complex comparator only
     */
    private static Set<CountryOrderByField> fieldComparePriorityOrder = new LinkedHashSet<>(Arrays.asList(NAME, LANGUAGE));

    static {

        comparatorsByField.put(NAME, getComparatorForNameField());
        comparatorsByField.put(LANGUAGE, getComparatorForLanguageField());
    }

    private CountryComparatorComponent() {
    }


    public static CountryComparatorComponent getInstance() {
        return INSTANCE;
    }

    private static Comparator<Country> getComparatorForNameField() {
        return new Comparator<Country>() {
            @Override
            public int compare(Country country1, Country country2) {
                return getComparatorForNullableStrings().compare(country1.getName(), country2.getName());
            }
        };
    }

    private static Comparator<Country> getComparatorForLanguageField() {
        return new Comparator<Country>() {
            @Override
            public int compare(Country country1, Country country2) {
                return getComparatorForNullableStrings().compare(country1.getLanguage(), country2.getLanguage());
            }
        };
    }

    public Comparator<Country> getComparatorForField(CountryOrderByField field) {
        return comparatorsByField.get(field);
    }

    public Comparator<Country> getComplexComparator(CountryOrderByField field) {
        return new Comparator<Country>() {

            @Override
            public int compare(Country m1, Country m2) {
                int result = 0;
                Comparator<Country> countryComparator = comparatorsByField.get(field);

                if (countryComparator != null) {
                    result = countryComparator.compare(m1, m2);
                    if (result == 0) {
                        for (CountryOrderByField otherField : fieldComparePriorityOrder) {
                            if (!otherField.equals(field)) {
                                result = comparatorsByField.get(otherField).compare(m1, m2);
                                if (result != 0) {
                                    break;
                                }
                            }
                        }

                    }
                }


                return result;
            }
        };
    }
}
