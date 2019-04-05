package ru.gorbach.hw17.city.repo.impl.memory;

import ru.gorbach.hw17.city.domain.City;
import ru.gorbach.hw17.city.repo.CityRepo;
import ru.gorbach.hw17.city.search.CitySearchCondition;
import ru.gorbach.hw17.common.business.search.Paginator;
import ru.gorbach.hw17.common.solutions.utils.CollectionUtils;
import ru.gorbach.hw17.storage.SequenceGenerator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static ru.gorbach.hw17.common.solutions.utils.StringUtils.isNotBlank;
import static ru.gorbach.hw17.storage.Storage.cityList;

public class CityCollectionRepo implements CityRepo {
    private CityOrderingComponent orderingComponent = new CityOrderingComponent();

    @Override
    public City add(City city) {
        city.setId(SequenceGenerator.generateId());
        cityList.add(city);
        return city;
    }

    @Override
    public void add(Collection<City> citiesList) {
        for (City city : citiesList){
            add(city);
        }
    }

    @Override
    public void update(City city) {
        // nothing to update
    }

    @Override
    public City findById(Long id) {
        return findCityById(id);
    }

    @Override
    public List<City> search(CitySearchCondition searchCondition) {
        if (searchCondition.getId() != null) {
            return Collections.singletonList(findById(searchCondition.getId()));
        } else {
            List<City> result = doSearch(searchCondition);

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
        City city = findCityById(id);
        if (city != null) {
            cityList.remove(city);
        }
    }

    @Override
    public void printAll() {
        for (City city : cityList) {
            System.out.println(city);
        }
    }

    @Override
    public List<City> findAll() {
        return cityList;
    }

    @Override
    public int countAll() {
        return cityList.size();
    }


    private List<City> doSearch(CitySearchCondition searchCondition) {
        boolean searchByName = isNotBlank(searchCondition.getName());
        boolean searchByPopulation = (searchCondition.getPopulation() != null);
        boolean searchByCapital = (searchCondition.isCapital() != null);

        List<City> result = new ArrayList<>();

        for (City city : cityList) {
            if (city != null) {
                boolean found = true;

                if (searchByName) {
                    found = searchCondition.getName().equals(city.getName());
                }

                if (found && searchByPopulation) {
                    found = searchCondition.getPopulation().equals(city.getPopulation());
                }

                if (found && searchByCapital) {
                    found = searchCondition.isCapital().equals(city.isCapital());
                }

                if (found) {
                    result.add(city);
                }
            }
        }
        return result;
    }

    private List<City> getPageOfData(List<City> cities, Paginator paginator) {
        return CollectionUtils.getPageOfData(cities, paginator.getLimit(), paginator.getOffset());
    }

    private City findCityById(Long cityId) {
        for (City city : cityList) {
            if (Long.valueOf(cityId).equals(city.getId())) {
                return city;
            }
        }
        return null;
    }
}
