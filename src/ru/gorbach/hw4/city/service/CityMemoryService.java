package ru.gorbach.hw4.city.service;

import ru.gorbach.hw4.city.City;
import ru.gorbach.hw4.city.repo.CityMemoryRepo;
import ru.gorbach.hw4.order.Order;
import ru.gorbach.hw4.order.repo.OrderMemoryRepo;

public class CityMemoryService {
    private CityMemoryRepo cityRepo = new CityMemoryRepo();
    private OrderMemoryRepo orderRepo = new OrderMemoryRepo();

    public void addCity(City city) {
        cityRepo.addCity(city);

        if (city.getOrders() != null) {
            for (Order order : city.getOrders()) {
                orderRepo.addOrder(order);
            }
        }
    }

    public City findCityById(long id) {
        return cityRepo.findCityById(id);
    }

    public void deleteCity(City city) {
        cityRepo.deleteCity(city);
    }

    public void deleteCity(Long id) {
        cityRepo.deleteCity(id);
    }

    public void printCitys() {
        cityRepo.printCities();
    }
}
