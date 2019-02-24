package ru.gorbach.hw5.city.service;

import ru.gorbach.hw5.city.City;

public interface CityServiceI {
    void addCity(City city);

    City findCityById(long id);

    void deleteCity(City city);

    void deleteCity(Long id);

    void printCities();
}
