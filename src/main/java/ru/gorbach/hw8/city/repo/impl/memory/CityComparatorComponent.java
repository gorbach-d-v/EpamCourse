package ru.gorbach.hw8.city.repo.impl.memory;

import ru.gorbach.hw8.city.domain.City;
import ru.gorbach.hw8.city.search.CityOrderByField;

import java.util.*;

import static ru.gorbach.hw8.city.search.CityOrderByField.NAME;
import static ru.gorbach.hw8.city.search.CityOrderByField.POPULATION;
import static ru.gorbach.hw8.common.business.repo.memory.CommonComparatorHolder.getComparatorForNullableStrings;

public class CityComparatorComponent {
    private static final CityComparatorComponent INSTANCE = new CityComparatorComponent();
    private static Map<CityOrderByField, Comparator<City>> comparatorsByField = new HashMap<>();
    /**
     * For complex comparator only
     */
    private static Set<CityOrderByField> fieldComparePriorityOrder = new LinkedHashSet<>(Arrays.asList(NAME, POPULATION));

    static {

        comparatorsByField.put(NAME, getComparatorForNameField());
        comparatorsByField.put(POPULATION, getComparatorForPopulationField());
    }

    private CityComparatorComponent() {
    }


    public static CityComparatorComponent getInstance() {
        return INSTANCE;
    }

    private static Comparator<City> getComparatorForNameField() {
        return new Comparator<City>() {
            @Override
            public int compare(City city1, City city2) {
                return getComparatorForNullableStrings().compare(city1.getName(), city2.getName());
            }
        };
    }

    private static Comparator<City> getComparatorForPopulationField() {
        return new Comparator<City>() {
            @Override
            public int compare(City city1, City city2) {
                return Integer.compare(city1.getPopulation(), city2.getPopulation());
            }
        };
    }

    public Comparator<City> getComparatorForField(CityOrderByField field) {
        return comparatorsByField.get(field);
    }

    public Comparator<City> getComplexComparator(CityOrderByField field) {
        return new Comparator<City>() {

            @Override
            public int compare(City m1, City m2) {
                int result = 0;
                Comparator<City> cityComparator = comparatorsByField.get(field);

                if (cityComparator != null) {
                    result = cityComparator.compare(m1, m2);
                    if (result == 0) {
                        for (CityOrderByField otherField : fieldComparePriorityOrder) {
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
