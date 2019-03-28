package ru.gorbach.hw7.city.repo.impl;

import ru.gorbach.hw7.city.domain.City;
import ru.gorbach.hw7.city.repo.CityRepo;
import ru.gorbach.hw7.city.search.CitySearchCondition;
import ru.gorbach.hw7.storage.sequenceGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ru.gorbach.hw7.common.solutions.StringUtils.isNotBlank;
import static ru.gorbach.hw7.storage.Storage.cityList;

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

                    if (found && (searchByName || searchByPopulation || searchByCapital || searchByClimate)) {
                        result.add(city);
                    }
                }
            }
            return result;
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

    private City findCityById(Long cityId) {
        for (City city : cityList) {
            if (Long.valueOf(cityId).equals(city.getId())) {
                return city;
            }
        }
        return null;
    }
}
