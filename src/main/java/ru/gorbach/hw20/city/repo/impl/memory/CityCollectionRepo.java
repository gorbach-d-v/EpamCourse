package ru.gorbach.hw20.city.repo.impl.memory;

import ru.gorbach.hw20.city.domain.City;
import ru.gorbach.hw20.city.repo.CityRepo;
import ru.gorbach.hw20.city.search.CitySearchCondition;
import ru.gorbach.hw20.common.solutions.utils.CollectionUtils;
import ru.gorbach.hw20.common.business.search.Paginator;
import ru.gorbach.hw20.storage.SequenceGenerator;

import java.util.*;

import static ru.gorbach.hw20.common.solutions.utils.StringUtils.isNotBlank;
import static ru.gorbach.hw20.storage.Storage.cityList;

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
    public Optional<City> findById(Long id) {
        return findCityById(id);
    }

    @Override
    public List<City> search(CitySearchCondition searchCondition) {
        if (searchCondition.getId() != null) {
            Optional<City> optionalCountry = findById(searchCondition.getId());
            if (optionalCountry.isPresent()) {
                return Collections.singletonList(optionalCountry.get());
            } else {
                return Collections.emptyList();
            }
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
        Optional<City> foundOptional = findCityById(id);
        foundOptional.map(country -> cityList.remove(country));
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

    private Optional<City> findCityById(Long cityId) {
        return cityList.stream().filter(country -> Long.valueOf(cityId).equals(country.getId())).findAny();
    }
}
