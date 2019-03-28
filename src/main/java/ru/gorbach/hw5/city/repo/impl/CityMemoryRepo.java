package ru.gorbach.hw5.city.repo.impl;

import ru.gorbach.hw5.city.domain.City;
import ru.gorbach.hw5.city.repo.CityRepo;
import ru.gorbach.hw5.city.search.CitySearchCondition;
import ru.gorbach.hw5.common.Solutions.ArrayUtils;

import static ru.gorbach.hw5.common.Solutions.StringUtils.isNotBlank;
import static ru.gorbach.hw5.storage.Storage.cities;


public class CityMemoryRepo implements CityRepo {
    private int cityIndex = 0;

    @Override
    public void add(City city) {
        if (cityIndex == cities.length) {
            City[] newArrCities = new City[cities.length * 2];
            System.arraycopy(cities, 0, newArrCities, 0, cities.length);
            cities = newArrCities;
        }

        cities[cityIndex] = city;
        cityIndex++;
    }

    @Override
    public City findById(long id) {
        Integer cityIndex = findCityIndexById(id);
        if (cityIndex != null) {
            return cities[cityIndex];
        }

        return null;
    }

    @Override
    public City[] search(CitySearchCondition searchCondition) {
        if (searchCondition.getId() != null) {
            return new City[]{findById(searchCondition.getId())};
        } else {
           
            boolean searchByName = isNotBlank(searchCondition.getName());
            boolean searchByPopulation = (searchCondition.getPopulation() == null);
            boolean searchByCapital = (searchCondition.isCapital() == null);
            boolean searchByClimate = (searchCondition.getClimate() == null);
            

            City[] result = new City[cities.length];
            int resultIndex = 0;

            for (City city : cities) {
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
                        result[resultIndex] = city;
                        resultIndex++;
                    }
                }
            }

            if (resultIndex > 0) {
                City toReturn[] = new City[resultIndex];
                System.arraycopy(result, 0, toReturn, 0, resultIndex);
                return toReturn;
            }
        }
        return new City[0];
    }
    
    @Override
    public void deleteById(Long id) {
        Integer cityIndex = findCityIndexById(id);

        if (cityIndex != null) {
            deleteCityByIndex(cityIndex);
        }
    }

    @Override
    public void printAll(){
        for(City city : cities){
            System.out.println(city);
        }
    }

    private void deleteCityByIndex(int index) {
        ArrayUtils.removeElement(cities, index);
        cityIndex--;
    }

    private Integer findCityIndexById(Long cityId) {
        for (int i = 0; i < cities.length; i++) {
            if (cities[i].getId().equals(cityId)) {
                return i;
            }
        }
        return null;
    }
}
