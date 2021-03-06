package ru.gorbach.hw20.city.repo.impl.memory;

import ru.gorbach.hw20.city.search.CitySearchCondition;
import ru.gorbach.hw20.city.domain.City;
import ru.gorbach.hw20.city.search.CityOrderByField;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CityOrderingComponent {
    public void applyOrdering(List<City> cities, CitySearchCondition citySearchCondition) {
        Comparator<City> cityComparator = null;

        CityOrderByField field = citySearchCondition.getOrderByField();
        switch (citySearchCondition.getOrderType()) {

            case SIMPLE: {
                cityComparator = CityComparatorComponent.getInstance().getComparatorForField(field);
                break;
            }
            case COMPLEX: {
                cityComparator = CityComparatorComponent.getInstance().getComplexComparator(field);
                break;
            }
        }

        if (cityComparator != null) {
            switch (citySearchCondition.getOrderDirection()) {

                case ASC:
                    Collections.sort(cities, cityComparator);
                    break;
                case DESC:
                    Collections.sort(cities, cityComparator.reversed());
                    break;
            }
        }
    }
}
