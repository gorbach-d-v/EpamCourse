package ru.gorbach.hw9.city.repo.impl;

import ru.gorbach.hw9.city.domain.City;
import ru.gorbach.hw9.city.search.CitySearchCondition;
import ru.gorbach.hw9.city.repo.CityRepo;
import ru.gorbach.hw9.common.business.search.SortType;
import ru.gorbach.hw9.storage.sequenceGenerator;

import java.util.*;

import static ru.gorbach.hw9.common.solutions.StringUtils.isNotBlank;
import static ru.gorbach.hw9.storage.Storage.cityList;

public class CityMemoryListRepo implements CityRepo {
    private int cityIndex = 0;

    @Override
    public void add(City city) {
        city.setId(sequenceGenerator.generateId());
        cityList.add(city);
    }

    @Override
    public void update(City city) {
        // nothing to update
    }

    @Override
    public City findById(long id) {
        return findCityById(id);
    }

    @Override
    public List<City> search(CitySearchCondition searchCondition) {
        if (searchCondition.getId() != null) {
            return Collections.singletonList(findById(searchCondition.getId()));
        } else {

            boolean searchByName = isNotBlank(searchCondition.getName());
            boolean searchByPopulation = (searchCondition.getPopulation() != null);
            boolean searchByCapital = (searchCondition.isCapital() != null);
            boolean searchByClimate = (searchCondition.getClimate() != null);

            List<City> result = new ArrayList<>();
            int resultIndex = 0;

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

                    if (found && searchByClimate) {
                        found = searchCondition.getClimate().equals(city.getClimate());
                    }

                    if (found) {
                        result.add(city);
                    }
                }
            }

            return sortList(result,searchCondition);
        }
    }

    @Override
    public void deleteById(Long id) {
        City city = findCityById(id);
        if (city != null){
            cityList.remove(city);
        }
    }

    @Override
    public void printAll(){
        for(City city : cityList){
            System.out.println(city);
        }
    }

    private List<City> sortList(List<City> cities, CitySearchCondition searchCondition) {
        boolean sortById = searchCondition.isSortById();
        boolean sortByName = searchCondition.isSortByName();
        boolean sortByPopulation = searchCondition.isSortByPopulation();


        Comparator<City> cityComparatorId = new Comparator<City>() {
            @Override
            public int compare(City city1, City city2) {
                return city1.getId().compareTo(city2.getId());
            }
        };

        Comparator<City> cityComparatorName = new Comparator<City>() {
            @Override
            public int compare(City city1, City city2) {
                return city1.getName().compareTo(city2.getName());
            }
        };

        Comparator<City> cityComparatorPopulation = new Comparator<City>() {
            @Override
            public int compare(City city1, City city2) {
                return Integer.compare(city1.getPopulation(), city2.getPopulation());
            }
        };

        Set<City> sortCities;
        if (sortById) {
            sortCities = new TreeSet<>(cityComparatorId);
        } else if (sortByName) {
            sortCities = new TreeSet<>(cityComparatorName);
        } else {
            sortCities = new TreeSet<>(cityComparatorPopulation);
        }
        sortCities.addAll(cities);
        List<City> result = new ArrayList<>(sortCities);
        if (SortType.DESC.equals(searchCondition.getSortType())) {
            Collections.reverse(result);
        }
        return result;
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
