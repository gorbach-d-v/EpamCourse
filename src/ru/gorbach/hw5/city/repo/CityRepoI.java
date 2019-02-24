package ru.gorbach.hw5.city.repo;

import ru.gorbach.hw5.city.City;

public interface CityRepoI {
    void addCity(City city);

    City findCityById(long id);

    void deleteCity(City city);

    void deleteCity(Long id);

    void printCities();
}
