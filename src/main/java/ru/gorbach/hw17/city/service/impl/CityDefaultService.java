package ru.gorbach.hw17.city.service.impl;

import ru.gorbach.hw17.city.domain.City;
import ru.gorbach.hw17.city.exception.CityExceptionMeta;
import ru.gorbach.hw17.city.exception.unchecked.DeleteCityException;
import ru.gorbach.hw17.city.service.CityService;
import ru.gorbach.hw17.city.repo.CityRepo;
import ru.gorbach.hw17.city.search.CitySearchCondition;
import ru.gorbach.hw17.common.business.exception.ReservationUncheckedException;
import ru.gorbach.hw17.order.repo.OrderRepo;

import java.util.Collection;
import java.util.List;

public class CityDefaultService implements CityService {
    private CityRepo cityRepo;
    private OrderRepo orderRepo;

    public CityDefaultService(CityRepo cityRepo, OrderRepo orderRepo) {
        this.cityRepo = cityRepo;
        this.orderRepo = orderRepo;
    }

    @Override
    public City add(City city) {
        if (city != null) {
            cityRepo.add(city);
        }
        return city;
    }

    @Override
    public void add(Collection<City> citiesList) {
        if (citiesList!=null){
            for (City city : citiesList){
                add(city);
            }
        }
    }

    @Override
    public void update(City city) {
        if (city != null) {
            if (city.getId() != null) {
                cityRepo.update(city);
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
    public List<City> search(CitySearchCondition searchCondition) {
        return cityRepo.search(searchCondition);
    }

    @Override
    public void deleteById(Long id) throws ReservationUncheckedException {
        if (id != null) {

            boolean noOrders = orderRepo.countByCity(id) == 0;
            if (!noOrders) {
                throw new DeleteCityException(CityExceptionMeta.DELETE_CITY_CONSTRAINT_ERROR);
            }
            cityRepo.deleteById(id);
        }
    }

    @Override
    public void delete(City city) {
        if (city != null) {
            if (city.getId() != null) {
                cityRepo.deleteById(city.getId());
            }
        }
    }

    @Override
    public void delete(Collection<City> citiesList) {
        if (citiesList != null){
            for (City city : citiesList){
                delete(city);
            }
        }
    }

    @Override
    public void printAll() {
        cityRepo.printAll();
    }

    @Override
    public List<City> findAll() {
        return cityRepo.findAll();
    }

    @Override
    public int countAll() {
        return cityRepo.countAll();
    }
}
