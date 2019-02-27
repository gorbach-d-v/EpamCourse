package ru.gorbach.hw6.city.service.impl;

import ru.gorbach.hw6.city.domain.City;
import ru.gorbach.hw6.city.repo.CityRepo;
import ru.gorbach.hw6.city.search.CitySearchCondition;
import ru.gorbach.hw6.city.service.CityService;
import ru.gorbach.hw6.order.domain.Order;
import ru.gorbach.hw6.order.repo.OrderRepo;

import java.util.List;

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
    public City findById(Long id) {
        if (id != null) {
            return cityRepo.findById(id);
        } else {
            return null;
        }
    }

    @Override
    public List<City> search(CitySearchCondition searchCondition){return cityRepo.search(searchCondition); }

    @Override
    public void deleteById(Long id) {
        if (id != null) {
            cityRepo.deleteById(id);
        }
    }

    @Override
    public void delete(City city) {
        if (city.getId() != null) {
            cityRepo.deleteById(city.getId());
        }
    }

    @Override
    public void printAll() {
        cityRepo.printAll();
    }
}
