package ru.gorbach.hw5.city.repo;

import ru.gorbach.hw5.city.City;
import ru.gorbach.hw5.commons.ArrayUtils;

import static ru.gorbach.hw5.storage.Storage.cities;

public class CityMemoryRepo implements CityRepoI {
    private int cityIndex = 0;

    public void addCity(City city) {
        if (cityIndex == cities.length) {
            City[] newArrCities = new City[cities.length * 2];
            System.arraycopy(cities, 0, newArrCities, 0, cities.length);
            cities = newArrCities;
        }

        cities[cityIndex] = city;
        cityIndex++;
    }

    public City findCityById(long id) {
        Integer cityIndex = findCityIndexById(id);
        if (cityIndex != null) {
            return cities[cityIndex];
        }

        return null;
    }

    public void deleteCity(City city) {
        Integer foundIndex = findCityIndexByEntity(city);

        if (foundIndex != null) {
            deleteCityByIndex(foundIndex);
        }
    }

    public void deleteCity(Long id) {
        Integer cityIndex = findCityIndexById(id);

        if (cityIndex != null) {
            deleteCityByIndex(cityIndex);
        }
    }

    public void printCities(){
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

    private Integer findCityIndexByEntity(City city) {
        for (int i = 0; i < cities.length; i++) {
            if (cities[i].equals(city)) {
                return i;
            }
        }

        return null;
    }
}
