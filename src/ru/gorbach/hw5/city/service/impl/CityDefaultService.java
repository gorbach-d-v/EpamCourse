package ru.gorbach.hw5.city.service.impl;

import ru.gorbach.hw5.city.domain.City;
import ru.gorbach.hw5.city.repo.CityRepo;
import ru.gorbach.hw5.city.search.CitySearchCondition;
import ru.gorbach.hw5.city.service.CityService;
import ru.gorbach.hw5.order.domain.Order;
import ru.gorbach.hw5.order.repo.OrderRepo;

public class CityDefaultService implements CityService {
    private CityRepo cityRepo;
    private OrderRepo orderRepo;

    @Override
    public void add(City city) {
        cityRepo.add(city);

        if (city.getOrders() != null) {
            for (Order order : city.getOrders()) {
                orderRepo.add(order);
            }
        }
    }

    @Override
    public City findById(long id) {
        return cityRepo.findById(id);
    }

    @Override
    public City[] search(CitySearchCondition searchCondition){return cityRepo.search(searchCondition); }

    @Override
    public void deleteById(Long id) {
        cityRepo.deleteById(id);
    }

    @Override
    public void printAll() {
        cityRepo.printAll();
    }
}
